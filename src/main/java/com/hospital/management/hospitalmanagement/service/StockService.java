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
}
