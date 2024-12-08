package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public String stockPage(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("totalQuantity", stockService.getTotalStockQuantity()); // 총 수량 추가
        return "stock"; // stock.html
    }

    @PostMapping("/stock/add")
    public String addStock(@ModelAttribute Stock stock) {
        stockService.addOrUpdateStock(stock); // 중복 처리 로직 호출
        return "redirect:/stock"; // 재고 목록 페이지로 리다이렉트
    }

}
