package com.hospital.management.hospitalmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public String beds(Model model) {
        Map<String, Object> emptyMap = new HashMap<>();
        model.addAttribute("bedMap", emptyMap);  // "bedMap"이라는 이름으로 빈 맵 전달
        return "beds";  // "beds"라는 뷰로 반환
    }
}
