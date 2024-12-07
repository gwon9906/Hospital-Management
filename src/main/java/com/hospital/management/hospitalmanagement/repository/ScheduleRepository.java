package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 근무일자 범위와 근무유형으로 스케줄 조회
    List<Schedule> findByWorkDateBetweenAndWorkType(LocalDateTime startDate, LocalDateTime endDate, String workType);

    // 간호사 이름으로 스케줄 조회
    List<Schedule> findByNurse_Name(String nurseName);

    // 간호사 ID와 근무일자 범위로 스케줄 조회
    List<Schedule> findByNurse_IdAndWorkDateBetween(Long nurseId, LocalDateTime startDate, LocalDateTime endDate);

    // 간호사 이름과 근무일자 범위로 스케줄 조회 (추가 기능)
    List<Schedule> findByNurse_NameAndWorkDateBetween(String nurseName, LocalDateTime startDate, LocalDateTime endDate);

    // 간호사 이름과 근무일자 범위, 근무유형으로 스케줄 조회 (추가 기능)
    List<Schedule> findByNurse_NameAndWorkDateBetweenAndWorkType(String nurseName, LocalDateTime startDate, LocalDateTime endDate, String workType);
}
