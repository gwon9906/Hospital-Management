package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.service.StockService;
import com.hospital.management.hospitalmanagement.service.AlarmLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class StockController {

    private final StockService stockService;
    private final AlarmLogService alarmLogService;

    public StockController(StockService stockService, AlarmLogService alarmLogService) {
        this.stockService = stockService;
        this.alarmLogService = alarmLogService;
    }

    @GetMapping("/stock")
    public String stockPage(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("lowStockItems", stockService.getLowStockItems());
        model.addAttribute("totalQuantity", stockService.getTotalStockQuantity());
        model.addAttribute("alarmLogs", alarmLogService.getAllAlarmLogs());
        return "stock";
    }

    @PostMapping("/stock/add")
    public String addStock(@ModelAttribute Stock stock, RedirectAttributes redirectAttributes) {
        try {
            stockService.addStock(stock);
            redirectAttributes.addFlashAttribute("successMessage", "재고가 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "재고 추가 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/stock";
    }
}