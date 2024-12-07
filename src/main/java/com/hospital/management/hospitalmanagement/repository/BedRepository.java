package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.domain.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByRoomNumberAndDayOutIsNull(Integer roomNumber);

    Bed findByRoomNumberAndBedNumberAndDayOutIsNull(Integer roomNumber, Integer bedNumber);
}
