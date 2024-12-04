package com.hospital.management.hospitalmanagement.controller;


import com.hospital.management.hospitalmanagement.model.Nurse;
import com.hospital.management.hospitalmanagement.service.NurseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;

    // 생성자 주입
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    // 간호사 목록 조회
    @GetMapping
    public String listNurses(Model model) {
        model.addAttribute("nurses", nurseService.getAllNurses());
        return "nurses/list"; // Thymeleaf 템플릿 호출
    }

    // 간호사 등록 폼 페이지
    @GetMapping("/create")
    public String createNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "nurses/create";
    }

    // 간호사 등록 처리
    @PostMapping("/create")
    public String createNurse(@ModelAttribute Nurse nurse) {
        nurseService.createNurse(nurse);
        return "redirect:/nurses";
    }

    // 간호사 삭제
    @GetMapping("/delete/{id}")
    public String deleteNurse(@PathVariable Long id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses";
    }
}