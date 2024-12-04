package com.hospital.management.hospitalmanagement.repository;

import com.hospital.management.hospitalmanagement.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    // 추가 쿼리가 필요하면 메서드 작성
}
