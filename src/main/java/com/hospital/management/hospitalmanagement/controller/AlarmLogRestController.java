package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.AlarmLog;
import com.hospital.management.hospitalmanagement.service.AlarmLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlarmLogRestController {

    private final AlarmLogService alarmLogService;

    public AlarmLogRestController(AlarmLogService alarmLogService) {
        this.alarmLogService = alarmLogService;
    }

    @GetMapping("/alarms")
    public List<AlarmLog> getAlarmLogs() {
        return alarmLogService.getAllAlarmLogs();
    }
}