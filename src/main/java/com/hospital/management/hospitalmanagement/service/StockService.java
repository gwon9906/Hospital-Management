package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 새로운 재고 추가
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // 재고 차감 메서드 추가
    public Stock updateStockQuantity(Long stockId, int deductedQuantity) {
        // 재고 조회
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

    public Stock getStockById(Long stockId) {
    return stockRepository.findById(stockId)
            .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));
}


}
