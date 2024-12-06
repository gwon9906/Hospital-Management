package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.domain.Bed;
import com.hospital.management.hospitalmanagement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//public class BedController {
//    @Autowired
//    private BedService bedService;
//
//    @GetMapping("/show")
//    public String showBed(@RequestParam(name = "dp", required = true) String dp,
//                          @RequestParam(name = "room", required = true) Integer room,
//                          Model model){
//        // 방번호로 검색
//        List<Bed> beds = bedService.findByRoomNumberAndDayOutIsNull(room);
//
//        // 디버깅: 검색된 데이터 로그 출력
//        System.out.println("검색된 병상 데이터: ");
//        for (Bed bed : beds) {  // 수정: bed -> beds
//            System.out.println(bed);
//        }
//
//        // 검색 결과를 모델에 추가
//        model.addAttribute("beds", beds);
//
//        // 같은 페이지로 이동
//        return "beds";
//    }
//}
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
}
