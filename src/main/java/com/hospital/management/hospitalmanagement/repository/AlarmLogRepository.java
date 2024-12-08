package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.AlarmLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmLogRepository extends JpaRepository<AlarmLog, Long> {
    List<AlarmLog> findAllByOrderByCreatedDateDesc();
}
