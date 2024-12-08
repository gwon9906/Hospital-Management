package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Nurse, Long> {

    // 진료과 ID를 기준으로 간호사 리스트를 찾는 메서드
    List<Nurse> findByDepartmentId(Long departmentId);
}
