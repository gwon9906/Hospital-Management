package com.hospital.management.hospitalmanagement.service;

import org.springframework.stereotype.Service;
import com.hospital.management.hospitalmanagement.repository.TeamRepository;
import com.hospital.management.hospitalmanagement.model.Nurse;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Nurse> getTeamByDepartmentID(Long departmentID) {
        // 'findByDepartmentID'를 'findByDepartmentId'로 수정
        return teamRepository.findByDepartmentId(departmentID);
    }
}
