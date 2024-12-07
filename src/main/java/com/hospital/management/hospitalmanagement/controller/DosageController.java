package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DosageController {

    private final StockService stockService;

    public DosageController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/dosage")
    public String dosagePage(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "dosage"; // 투약량 계산 페이지
    }

    @PostMapping("/dosage/calculate")
    public String calculateDosage(
            @RequestParam Long stockId,
            @RequestParam double weight,
            @RequestParam double ratePerHour,
            @RequestParam double hours,
            Model model) {
        double totalDosage = weight * ratePerHour * hours;
        model.addAttribute("stock", stockService.getStockById(stockId));
        model.addAttribute("totalDosage", totalDosage);
        model.addAttribute("weight", weight);
        model.addAttribute("ratePerHour", ratePerHour);
        model.addAttribute("hours", hours);
        return "dosage";
    }

    @PostMapping("/dosage/prescribe")
    public String prescribeDosage(
            @RequestParam Long stockId,
            @RequestParam double dosage,
            Model model) {
        try {
            stockService.updateStockQuantity(stockId, (int) -dosage);
            model.addAttribute("success", "처방이 완료되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("stocks", stockService.getAllStocks());
        return "dosage";
    }
}
