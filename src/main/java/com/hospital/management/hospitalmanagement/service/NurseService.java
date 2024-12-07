package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.repository.ScheduleRepository;
import com.hospital.management.hospitalmanagement.repository.NurseRepository;
import com.hospital.management.hospitalmanagement.model.Schedule;
import com.hospital.management.hospitalmanagement.model.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private NurseRepository nurseRepository;

    // 간호사 이름과 날짜 범위로 근무 일정 조회
    public List<Schedule> getWorkSchedulesForMonth(String nurseName, LocalDate startDate, LocalDate endDate) {
        // 간호사 이름으로 간호사 조회 (대소문자 구분 없음)
        Optional<Nurse> optionalNurse = nurseRepository.findByNameIgnoreCase(nurseName);

        // 간호사 존재 여부 확인
        if (optionalNurse.isEmpty()) {
            throw new RuntimeException("Nurse not found");
        }

        Nurse nurse = optionalNurse.get();

        // LocalDateTime으로 시작일과 종료일을 설정
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // 간호사 ID와 근무일자 범위로 근무 일정 조회
        return scheduleRepository.findByNurse_IdAndWorkDateBetween(nurse.getId(), startDateTime, endDateTime);
    }
}
