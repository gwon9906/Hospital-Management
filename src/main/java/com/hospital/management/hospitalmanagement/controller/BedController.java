package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.domain.Bed;
import com.hospital.management.hospitalmanagement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BedController {

    @Autowired
    private BedService bedService;

    @GetMapping("/show")
    public String showBed(@RequestParam(name = "dp", required = true) String dp,
                          @RequestParam(name = "room", required = true) Integer room,
                          Model model){

        // 방번호로 검색
        List<Bed> beds = bedService.findByRoomNumberAndDayOutIsNull(room);

        // 디버깅: 검색된 데이터 로그 출력
        System.out.println("검색된 병상 데이터: ");
        for (Bed bed : beds) {
            System.out.println(bed);
        }

        // 각 병상 번호별로 데이터를 매핑
        Map<Integer, Bed> bedMap = new HashMap<>();
        for (Bed bed : beds) {
            bedMap.put(bed.getBedNumber(), bed); // 병상 번호를 키로, 병상 객체를 값으로 저장
        }

        // 모델에 병상 데이터를 추가
        model.addAttribute("bedMap", bedMap);

        // 같은 페이지로 이동
        return "beds";
    }

    // 각 병상별 상세 정보 페이지로 이동
    @GetMapping("/bedDetails")
    public String bedDetails(@RequestParam(name = "roomNumber") Integer roomNumber,
                             @RequestParam(name = "bedNumber") Integer bedNumber,
                             Model model) {
        // 퇴원일이 NULL인 병상 데이터 검색
        Bed bed = bedService.findByRoomNumberAndBedNumberAndDayOutIsNull(roomNumber, bedNumber);

        if (bed == null) {
            throw new IllegalArgumentException("현재 입원 중인 환자가 없습니다: 병실 " + roomNumber + ", 병상 " + bedNumber);
        }

        // 검색된 병상 데이터를 모델에 추가
        model.addAttribute("bed", bed);

        // 병상 상세 정보 페이지로 이동
        return "bedDetails";
    }

    //병상 이동 및 퇴원 수속 처리
    @PostMapping("/move")
    @ResponseBody
    public ResponseEntity<Void> moveBed(@RequestBody Map<String, Object> requestData) {
        try {
            Long patientId = null;
            // patientId가 null인 경우를 체크합니다.
            if (requestData.containsKey("patientId")) {
                patientId = ((Number) requestData.get("patientId")).longValue();
            }

            Integer newRoomNumber = (Integer) requestData.get("newRoomNumber");
            Integer newBedNumber = (Integer) requestData.get("newBedNumber");

            if (patientId == null || newRoomNumber == null || newBedNumber == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 입력 값이 올바르지 않으면 400 오류 반환
            }

            bedService.moveBed(patientId, newRoomNumber, newBedNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();  // 예외 로그 확인
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 오류 반환
        }
    }

    @PostMapping("/discharge")
    @ResponseBody
    public ResponseEntity<String> dischargePatient(@RequestBody Map<String, Object> requestData) {
        //디버깅 코드
        System.out.println("Request Data: " + requestData);

        Long patientId; //NULL인 경우 예외 처리
        try {
            patientId = ((Number) requestData.get("patientId")).longValue();
        } catch (NullPointerException | ClassCastException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid patientId.");
        }

        try {
            bedService.dischargePatient(patientId);
            // 성공 시 단순히 "OK" 응답
            return ResponseEntity.ok("퇴원 처리 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("퇴원 처리에 실패했습니다.");
        }
    }
}
