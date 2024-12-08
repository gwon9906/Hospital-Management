package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContaining(String name);
    List<Patient> findByNameContainingAndId(String name, Long id);
}

