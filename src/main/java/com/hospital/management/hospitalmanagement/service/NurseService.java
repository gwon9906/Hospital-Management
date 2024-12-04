package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Nurse;
import com.hospital.management.hospitalmanagement.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    // 생성자 주입 (Dependency Injection)
    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    // 전체 간호사 목록 조회
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    // 간호사 등록
    public Nurse createNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    // 특정 간호사 조회
    public Nurse getNurseById(Long id) {
        return nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + id));
    }

    // 간호사 삭제
    public void deleteNurse(Long id) {
        nurseRepository.deleteById(id);
    }
}