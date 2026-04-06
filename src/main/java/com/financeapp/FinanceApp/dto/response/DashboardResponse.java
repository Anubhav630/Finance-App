package com.financeapp.FinanceApp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {
    private double totalIncome;
    private double totalExpense;
    private double netBalance;
}