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

}
