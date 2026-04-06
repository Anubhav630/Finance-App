package com.financeapp.FinanceApp.controller;

import com.financeapp.FinanceApp.dto.response.DashboardResponse;
import com.financeapp.FinanceApp.dto.response.RecordResponse;
import com.financeapp.FinanceApp.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardResponse summary() {

        double income = dashboardService.getTotalIncome();
        double expense = dashboardService.getTotalExpense();

        return DashboardResponse.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .netBalance(income - expense)
                .build();
    }

    @GetMapping("/categories")
    public Map<String, Double> categoryTotals() {
        return dashboardService.getCategoryTotals();
    }

    @GetMapping("/recent")
    public List<RecordResponse> recent() {
        return dashboardService.getRecentRecords();
    }
}