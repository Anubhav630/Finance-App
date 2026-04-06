package com.financeapp.FinanceApp.repository;

import com.financeapp.FinanceApp.entity.Record;
import com.financeapp.FinanceApp.entity.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByType(RecordType type);

    List<Record> findByCategory(String category);

    List<Record> findByDateBetween(LocalDate start, LocalDate end);
}
