package com.financeapp.FinanceApp.controller;

import com.financeapp.FinanceApp.dto.request.CreateRecordRequest;
import com.financeapp.FinanceApp.dto.response.RecordResponse;
import com.financeapp.FinanceApp.entity.RecordType;
import com.financeapp.FinanceApp.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public RecordResponse create(@Valid @RequestBody CreateRecordRequest request) {
        return recordService.createRecord(request);
    }
    @PutMapping("/{id}")
    public RecordResponse update(@PathVariable Long id,
                                 @Valid @RequestBody CreateRecordRequest request) {
        return recordService.updateRecord(id, request);
    }

    @GetMapping("/type/{type}")
    public List<RecordResponse> getByType(@PathVariable RecordType type) {
        return recordService.getByType(type);
    }

    @GetMapping("/category/{category}")
    public List<RecordResponse> getByCategory(@PathVariable String category) {
        return recordService.getByCategory(category);
    }

    @GetMapping
    public List<RecordResponse> getAllRecords() {
        return recordService.getAllRecords();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return "Record deleted successfully";
    }

    @GetMapping("/date")
    public List<RecordResponse> getByDateRange(
            @RequestParam String start,
            @RequestParam String end) {

        return recordService.getByDateRange(
                java.time.LocalDate.parse(start),
                java.time.LocalDate.parse(end)
        );
    }

}