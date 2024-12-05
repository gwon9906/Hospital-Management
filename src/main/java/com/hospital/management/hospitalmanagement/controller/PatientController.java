package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.domain.Patient;
import com.hospital.management.hospitalmanagement.repository.PatientRepository;
import com.hospital.management.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/search")
    public String searchPatients(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "id", required = false) Long id,
                                 Model model) {
        List<Patient> patients = new ArrayList<>();

        if ((name == null || name.isBlank()) && (id == null)) {
            // 검색 조건이 없으면 빈 리스트 반환
            patients = List.of();
        } else if ((name != null && !name.isEmpty()) && (id == null)) {
            // 이름으로 검색
            patients = patientService.findByNameContaining(name);
        } else if ((name == null || name.isEmpty()) && (id != null)) {
            // ID로 검색
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            patients = optionalPatient.map(List::of).orElseGet(List::of);  // Optional 처리
        } else {
            // 이름+ID로 검색
            patients = patientService.findByNameContainingAndId(name, id);
        }

        // 검색 결과를 모델에 추가
        model.addAttribute("patients", patients);

        // 같은 페이지로 이동
        return "patients";
    }
}

