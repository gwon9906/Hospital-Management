package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.service.StockService;
import com.hospital.management.hospitalmanagement.service.AlarmLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("alarmLogs", alarmLogService.getAllAlarmLogs()); // 알람 로그 추가
        return "stock";
    }
}
