package com.example.stock.service.controller;

import com.example.stock.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/consolidated")
    public List<Map<String, Object>> getConsolidatedReport() {
        return reportService.generateConsolidatedReport();
    }
}
