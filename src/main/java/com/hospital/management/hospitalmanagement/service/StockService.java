package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.repository.StockRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final JdbcTemplate jdbcTemplate;

    public StockService(StockRepository stockRepository, JdbcTemplate jdbcTemplate) {
        this.stockRepository = stockRepository;
        this.jdbcTemplate = jdbcTemplate;
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

    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("약품을 찾을 수 없습니다."));
    }

    public List<Stock> getLowStockItems() {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() <= 10)
                .toList();
    }

    public void callInventoryUpdateProcedure(Long stockId, double dosage) {
        jdbcTemplate.update("CALL update_inventory(?, ?)", stockId, dosage);
    }
    @Transactional
    public Stock addStock(Stock newStock) {
        if (newStock.getQuantity() < 0) {
            throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
        }

        // 같은 약품명으로 검색
        Optional<Stock> existingStock = stockRepository.findByName(newStock.getName());

        if (existingStock.isPresent()) {
            // 기존 재고가 있는 경우
            Stock stock = existingStock.get();

            // 수량 합산
            stock.setQuantity(stock.getQuantity() + newStock.getQuantity());

            // 새로운 정보로 업데이트
            stock.setExpiryDate(newStock.getExpiryDate());
            stock.setLocation(newStock.getLocation());

            return stockRepository.save(stock);
        } else {
            // 새로운 약품인 경우
            return stockRepository.save(newStock);
        }
    }

}
