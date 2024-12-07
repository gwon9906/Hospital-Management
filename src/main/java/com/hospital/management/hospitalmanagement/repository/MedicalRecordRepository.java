package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.domain.MedicalRecord;
import com.hospital.management.hospitalmanagement.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientOrderByDateDesc(Patient patient);
}