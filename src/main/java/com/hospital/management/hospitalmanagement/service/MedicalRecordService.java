package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.domain.MedicalRecord;
import com.hospital.management.hospitalmanagement.domain.Patient;
import com.hospital.management.hospitalmanagement.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> findByPatientOrderByDateDesc(Patient patient) {
        return medicalRecordRepository.findByPatientOrderByDateDesc(patient);
    }
}
