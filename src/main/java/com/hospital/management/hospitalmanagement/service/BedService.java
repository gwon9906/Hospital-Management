package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.domain.Bed;
import com.hospital.management.hospitalmanagement.repository.BedRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private EntityManager entityManager;

    //방 번호로 검색
    public List<Bed> findByRoomNumberAndDayOutIsNull(Integer roomNumber) {
        return bedRepository.findByRoomNumberAndDayOutIsNull(roomNumber);
    }

    //입원 환자 개인 검색
    public Bed findByRoomNumberAndBedNumberAndDayOutIsNull(Integer roomNumber, Integer bedNumber) {
        return bedRepository.findByRoomNumberAndBedNumberAndDayOutIsNull(roomNumber, bedNumber);
    }

    public void moveBed(Long patientId, Integer newRoomNumber, Integer newBedNumber) {
        try {
            entityManager.createStoredProcedureQuery("MOVE_BED")
                    .registerStoredProcedureParameter("p_patient_id", Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_new_room_number", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_new_bed_number", Integer.class, ParameterMode.IN)
                    .setParameter("p_patient_id", patientId)
                    .setParameter("p_new_room_number", newRoomNumber)
                    .setParameter("p_new_bed_number", newBedNumber)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();  // 예외 로그 확인
            throw e;  // 예외를 던져서 상위 컨트롤러에서 처리
        }
    }

    @Transactional
    public void dischargePatient(Long patientId) {
        Query query = entityManager.createQuery(
                "UPDATE Bed b SET b.dayOut = CURRENT_DATE WHERE b.patient.id = :patientId AND b.dayOut IS NULL"
        );
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }
}
