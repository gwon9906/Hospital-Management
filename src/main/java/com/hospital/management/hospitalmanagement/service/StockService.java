package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    // 생성자 주입
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // 전체 재고 조회
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // 재고의 총 수량 계산
    public int getTotalStockQuantity() {
        return stockRepository.findAll()
                .stream()
                .mapToInt(Stock::getQuantity)
                .sum();
    }

    // 새로운 재고 추가 또는 업데이트
    public Stock addOrUpdateStock(Stock stock) {
        // 약품명을 기준으로 기존 데이터를 조회
        Optional<Stock> existingStock = stockRepository.findByName(stock.getName());

        if (existingStock.isPresent()) {
            // 기존 데이터가 있을 경우 수량과 기타 정보 업데이트
            Stock existing = existingStock.get();
            existing.setQuantity(existing.getQuantity() + stock.getQuantity());
            existing.setExpiryDate(stock.getExpiryDate()); // 유효기간 업데이트
            existing.setLocation(stock.getLocation()); // 보관위치 업데이트
            return stockRepository.save(existing); // 업데이트된 데이터 저장
        } else {
            // 기존 데이터가 없을 경우 새 데이터 추가
            return stockRepository.save(stock);
        }
    }

    // 재고 차감 메서드 추가
    public Stock updateStockQuantity(Long stockId, int deductedQuantity) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));

        // 재고 부족 여부 확인
        if (stock.getQuantity() < deductedQuantity) {
            throw new RuntimeException("재고가 부족합니다. 약품명: " + stock.getName());
        }

        // 재고 차감
        stock.setQuantity(stock.getQuantity() - deductedQuantity);
        return stockRepository.save(stock); // 업데이트된 재고 반환
    }

    // 특정 약품 조회
    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));
    }
}
