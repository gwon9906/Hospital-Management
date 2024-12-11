package com.hospital.management.hospitalmanagement.service;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.repository.StockRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
