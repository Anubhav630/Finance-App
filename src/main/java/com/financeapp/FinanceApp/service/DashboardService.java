package com.financeapp.FinanceApp.service;

import com.financeapp.FinanceApp.dto.response.RecordResponse;
import com.financeapp.FinanceApp.entity.Record;
import com.financeapp.FinanceApp.entity.RecordType;
import com.financeapp.FinanceApp.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final RecordRepository recordRepository;

    public double getTotalIncome() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(Record::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(Record::getAmount)
                .sum();
    }

    public Map<String, Double> getCategoryTotals() {
        return recordRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Record::getCategory,
                        Collectors.summingDouble(Record::getAmount)
                ));
    }

    public List<RecordResponse> getRecentRecords() {
        return recordRepository.findAll().stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .map(this::mapToResponse)
                .toList();
    }

    private RecordResponse mapToResponse(Record record) {
        return RecordResponse.builder()
                .id(record.getId())
                .amount(record.getAmount())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .build();
    }
}