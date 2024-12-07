package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Optional<Nurse> findByNameIgnoreCase(String name);  // Optional<Nurse>로 변경
}