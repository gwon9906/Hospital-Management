package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.service.NurseService;
import com.hospital.management.hospitalmanagement.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    /**
     * 특정 간호사의 근무일정을 조회하는 API 엔드포인트
     * @param nurseName 간호사 이름
     * @param startDate 조회 시작 날짜
     * @param endDate 조회 종료 날짜
     * @return 간호사의 근무 일정
     */
    @GetMapping("/{nurseName}/schedule")
    public ResponseEntity<List<Schedule>> getWorkSchedule(
            @PathVariable String nurseName,  // URL에서 간호사 이름을 받음
            @RequestParam String startDate,  // 쿼리 파라미터로 시작 날짜 받음
            @RequestParam String endDate) {  // 쿼리 파라미터로 종료 날짜 받음

        // 문자열 날짜를 LocalDate로 변환
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // 간호사의 근무 일정 조회
        List<Schedule> schedules = nurseService.getWorkSchedulesForMonth(nurseName, start, end);

        return ResponseEntity.ok(schedules);  // 조회된 근무 일정을 응답으로 반환
    }
}
