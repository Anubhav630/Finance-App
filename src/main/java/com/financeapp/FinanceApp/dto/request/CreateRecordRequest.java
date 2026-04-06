package com.financeapp.FinanceApp.dto.request;

import com.financeapp.FinanceApp.entity.RecordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateRecordRequest {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private RecordType type;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    private String notes;
}