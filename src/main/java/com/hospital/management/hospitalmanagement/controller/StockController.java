package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public String stockPage(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("lowStockItems", stockService.getLowStockItems()); // 임계치 이하 항목 전달
        model.addAttribute("totalQuantity", stockService.getTotalStockQuantity());
        return "stock"; // stock.html
    }
}
