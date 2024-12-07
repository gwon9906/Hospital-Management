package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.domain.Patient;
import com.hospital.management.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    //이름으로만 검색
    public List<Patient> findByNameContaining(String name) {
        return patientRepository.findByNameContaining(name);
    }

    //이름, Id로 검색
    public List<Patient> findByNameContainingAndId(String name, Long id) {
        return patientRepository.findByNameContainingAndId(name, id);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }
}

