package com.hospital.management.hospitalmanagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import com.hospital.management.hospitalmanagement.service.TeamService;
import com.hospital.management.hospitalmanagement.model.Department;
import com.hospital.management.hospitalmanagement.repository.DepartmentRepository;

import java.util.List;

@Controller
public class TeamController {
    private final TeamService teamService; // 팀 관련 비즈니스 로직 처리
    private final DepartmentRepository departmentRepository; // 진료과 데이터 처리

    // 생성자를 통해 필요한 의존성 주입
    public TeamController(TeamService teamService, DepartmentRepository departmentRepository) {
        this.teamService = teamService;
        this.departmentRepository = departmentRepository;
    }

    // "/team" 경로로 요청이 들어올 때 실행되는 메서드
    @GetMapping("/team")
    public String getTeamList(Model model) {
        // DB에서 모든 진료과 가져오기
        List<Department> departments = departmentRepository.findAll();

        // 모델에 진료과 리스트 추가
        model.addAttribute("departments", departments);

        // 각 진료과에 소속된 간호사 리스트를 모델에 추가
        for (Department department : departments) {
            // "teamList_<department.id>" 키로 간호사 리스트 저장
            model.addAttribute("teamList_" + department.getId(), teamService.getTeamByDepartmentID(department.getId()));
        }

        // "Team"이라는 이름의 Thymeleaf 템플릿 반환
        return "Team";
    }
}
