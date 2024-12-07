package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.Schedule;
import com.hospital.management.hospitalmanagement.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 캘린더 화면을 보여주는 메서드
    @GetMapping("/calendar")
    public String showCalendar(@RequestParam(required = false) String nurseName,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate,
                               @RequestParam(required = false) String shift,
                               Model model) {

        List<Schedule> schedules = null;

        try {
            // 간호사 이름과 날짜 범위가 주어졌을 때
            if (nurseName != null) {
                // 간호사 이름만 주어졌을 때
                System.out.println("Fetching schedules by nurseName: " + nurseName);
                schedules = scheduleService.getSchedulesByNurseName(nurseName);
            }
            else {
                // 간호사 이름, 날짜 범위가 없을 경우 (모든 스케줄)
                System.out.println("Fetching all schedules.");
                schedules = scheduleService.getAllSchedules();
            }

            if (schedules != null) {
                System.out.println("Fetched " + schedules.size() + " schedules.");
            } else {
                System.out.println("No schedules found.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while fetching schedules: " + e.getMessage());
            e.printStackTrace();
        }

        model.addAttribute("schedules", schedules);  // 모델에 스케줄 데이터 추가
        return "calendar";  // calendar.html을 반환
    }

    // /api/schedule 엔드포인트에서 JSON 데이터를 반환
    @GetMapping("/api/schedule")
    @ResponseBody
    public List<Map<String, Object>> getSchedules(@RequestParam(value = "nurseName", required = false) String nurseName,
                                                  @RequestParam(value = "shift", required = false) String shift,
                                                  @RequestParam(value = "startDate", required = false) String startDate,
                                                  @RequestParam(value = "endDate", required = false) String endDate) {

        List<Schedule> schedules = null;
        List<Map<String, Object>> events = new ArrayList<>();

        try {
            if (nurseName != null) {
                // 간호사 이름만 주어졌을 때
                System.out.println("Fetching schedules by nurseName: " + nurseName);
                schedules = scheduleService.getSchedulesByNurseName(nurseName);
            }
            else {
                // 간호사 이름, 날짜 범위가 없으면 모든 스케줄 조회
                System.out.println("Fetching all schedules.");
                schedules = scheduleService.getAllSchedules();
            }

            if (schedules != null && !schedules.isEmpty()) {
                System.out.println("Fetched " + schedules.size() + " schedules.");
            } else {
                System.out.println("No schedules found.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;  // ISO_DATE_TIME 포맷 사용

            for (Schedule schedule : schedules) {
                // 로그 출력 추가 (디버깅용)
                System.out.println("Processing schedule: " + schedule.getWorkType() + ", Work Date: " + schedule.getWorkDate());

                String nurseNameStr = (schedule.getNurse() != null) ? schedule.getNurse().getName() : "미정";  // 간호사 이름 처리

                Map<String, Object> event = new HashMap<>();
                if (schedule.getWorkDate() != null) {
                    String formattedWorkDate = schedule.getWorkDate().format(formatter);
                    String formattedEndTime = (schedule.getEndTime() != null) ? schedule.getEndTime().format(formatter) : formattedWorkDate;

                    event.put("title", schedule.getWorkType() + " - " + nurseNameStr);
                    event.put("start", formattedWorkDate);
                    event.put("end", formattedEndTime);
                    events.add(event);
                }
            }

        } catch (Exception e) {
            System.out.println("Error occurred while fetching schedules: " + e.getMessage());
            e.printStackTrace();
        }

        return events;  // FullCalendar가 사용할 이벤트 형식으로 반환
    }
}