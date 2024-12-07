package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.domain.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByRoomNumberAndDayOutIsNull(Integer roomNumber);

    Bed findByRoomNumberAndBedNumberAndDayOutIsNull(Integer roomNumber, Integer bedNumber);
}
