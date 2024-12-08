package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name); // 진료과명으로 진료과 조회
}
