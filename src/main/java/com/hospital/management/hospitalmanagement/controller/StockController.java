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
        return "stock"; // 기존 재고 관리 페이지
    }
}
