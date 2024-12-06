package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.domain.Bed;
import com.hospital.management.hospitalmanagement.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    private BedRepository bedRepository;

    //방 번호로 검색
    public List<Bed> findByRoomNumberAndDayOutIsNull(Integer roomNumber) {
        return bedRepository.findByRoomNumberAndDayOutIsNull(roomNumber);
    }
}
