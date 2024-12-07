package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Schedule;
import com.hospital.management.hospitalmanagement.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // 특정 날짜 범위와 근무유형으로 조회
    public List<Schedule> getSchedulesByShift(LocalDateTime startDateTime, LocalDateTime endDateTime, String shift) {
        return scheduleRepository.findByWorkDateBetweenAndWorkType(startDateTime, endDateTime, shift);
    }

    // 간호사 이름과 날짜 범위로 스케줄 조회
    public List<Schedule> getSchedulesByNurseNameAndDateRange(String nurseName, String startDate, String endDate, String shift) {
        // 날짜 형식 변환 (예시: "2024-12-07" -> LocalDateTime)
        LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59");

        // 간호사 이름과 날짜 범위, 근무유형으로 스케줄 조회
        return scheduleRepository.findByNurse_NameAndWorkDateBetweenAndWorkType(nurseName, start, end, shift);
    }

    // 간호사 이름으로 스케줄 조회
    public List<Schedule> getSchedulesByNurseName(String nurseName) {
        if (nurseName != null && !nurseName.isEmpty()) {
            return scheduleRepository.findByNurse_Name(nurseName);
        }
        return scheduleRepository.findAll();  // 이름이 없으면 모든 스케줄 반환
    }

    // 모든 스케줄 조회
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // 날짜 범위와 근무유형으로 스케줄 조회
    public List<Schedule> getSchedulesByDateRange(String startDate, String endDate, String shift) {
        // 날짜 형식 변환 (예시: "2024-12-07" -> LocalDateTime)
        LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59");

        // 날짜 범위와 근무유형으로 스케줄 조회
        return scheduleRepository.findByWorkDateBetweenAndWorkType(start, end, shift);
    }
}
