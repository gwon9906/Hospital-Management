package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.AlarmLog;
import com.hospital.management.hospitalmanagement.repository.AlarmLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmLogService {

    private final AlarmLogRepository alarmLogRepository;

    public AlarmLogService(AlarmLogRepository alarmLogRepository) {
        this.alarmLogRepository = alarmLogRepository;
    }

    public List<AlarmLog> getAllAlarmLogs() {
        return alarmLogRepository.findAllByOrderByCreatedDateDesc(); // 최신순 정렬
    }
}
