package com.financeapp.FinanceApp.service;

import com.financeapp.FinanceApp.dto.request.CreateRecordRequest;
import com.financeapp.FinanceApp.dto.response.RecordResponse;
import com.financeapp.FinanceApp.entity.Record;
import com.financeapp.FinanceApp.entity.RecordType;
import com.financeapp.FinanceApp.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponse createRecord(CreateRecordRequest request) {

        Record record = Record.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .notes(request.getNotes())
                .createdAt(LocalDateTime.now())
                .build();

        Record saved = recordRepository.save(record);

        return mapToResponse(saved);
    }

    public List<RecordResponse> getAllRecords() {
        return recordRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
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

    public RecordResponse updateRecord(Long id, CreateRecordRequest request) {

        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());

        Record saved = recordRepository.save(record);

        return mapToResponse(saved);
    }
    public List<RecordResponse> getByType(RecordType type) {
        return recordRepository.findByType(type)
                .stream().map(this::mapToResponse).toList();
    }

    public List<RecordResponse> getByCategory(String category) {
        return recordRepository.findByCategory(category)
                .stream().map(this::mapToResponse).toList();
    }

    public Map<String, Double> getCategoryTotals() {
        return recordRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Record::getCategory,
                        Collectors.summingDouble(Record::getAmount)
                ));
    }

    public List<Record> getRecentRecords() {
        return recordRepository.findAll().stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .toList();
    }
}