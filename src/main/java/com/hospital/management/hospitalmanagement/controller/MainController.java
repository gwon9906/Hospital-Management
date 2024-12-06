package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final StockService stockService;

    public MainController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html로 이동
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule"; // 근무표 페이지
    }

    @GetMapping("/patients")
    public String patients() {
        return "patients"; // 전체 환자 페이지
    }

//    @GetMapping("/beds")
//    public String beds() {
//        return "beds"; // 병상별 환자 페이지
//    }
    @GetMapping("/beds")
    public String beds(Model model) {
        Map<String, Object> emptyMap = new HashMap<>();
        model.addAttribute("bedMap", emptyMap);  // "bedMap"이라는 이름으로 빈 맵 전달
        return "beds";  // "beds"라는 뷰로 반환
    }

    @GetMapping("/dosage")
    public String dosage() {
        return "dosage"; // 약 용량 계산 페이지
    }

    @GetMapping("/stock")
    public String stockPage(Model model) {
        List<Stock> stocks = stockService.getAllStocks(); // 모든 재고 가져오기
        int totalQuantity = stockService.getTotalStockQuantity(); // 총 수량 계산
        model.addAttribute("stocks", stocks);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("newStock", new Stock()); // 재고 추가를 위한 빈 객체
        return "stock"; // templates/stock.html로 이동
    }

    @PostMapping("/stock/add")
    public String addStock(@ModelAttribute("newStock") Stock stock) {
        stockService.addStock(stock); // 새로운 재고 추가
        return "redirect:/stock"; // 추가 후 재고 확인 페이지로 리다이렉트
    }

    @GetMapping("/organization")
    public String organization() {
        return "organization"; // 진료과 조직도 페이지
    }
}
