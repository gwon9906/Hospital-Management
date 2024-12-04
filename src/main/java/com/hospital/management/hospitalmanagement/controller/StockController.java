package com.hospital.management.hospitalmanagement.controller;

import com.hospital.management.hospitalmanagement.model.Stock;
import com.hospital.management.hospitalmanagement.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

}
