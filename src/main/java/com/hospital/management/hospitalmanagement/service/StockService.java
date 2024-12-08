package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public int getTotalStockQuantity() {
        return stockRepository.findAll()
                .stream()
                .mapToInt(Stock::getQuantity)
                .sum();
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStockQuantity(Long stockId, int deductedQuantity) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));

        if (stock.getQuantity() < deductedQuantity) {
            throw new RuntimeException("재고가 부족합니다. 약품명: " + stock.getName());
        }

        stock.setQuantity(stock.getQuantity() - deductedQuantity);
        return stockRepository.save(stock);
    }

    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));
    }

    // 임계치 이하 약품 목록 조회
    public List<Stock> getLowStockItems() {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() <= 10) // 10 이하인 항목만
                .toList();
    }
}
