package com.hospital.management.hospitalmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html 로 이동
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule"; // 근무표 페이지
    }

    @GetMapping("/patients")
    public String patients() {
        return "patients"; // 전체 환자 검색 페이지
    }

    @GetMapping("/beds")
    public String beds() {
        return "beds"; // 병상별 환자 페이지
    }

    @GetMapping("/organization")
    public String organization() {
        return "organization"; // 진료과 조직도 페이지
    }
}
