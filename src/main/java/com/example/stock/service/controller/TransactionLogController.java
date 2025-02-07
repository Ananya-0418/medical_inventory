package com.example.stock.service.controller;

import com.example.stock.dao.model.TransactionLog;
import com.example.stock.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionLogs")
public class TransactionLogController {

    @Autowired
    private TransactionLogService transactionLogService;

    @GetMapping
    public ResponseEntity<List<TransactionLog>> getAllTransactionLogs() {
        List<TransactionLog> transactionLogs = transactionLogService.getAllTransactionLogs();
        return ResponseEntity.ok(transactionLogs);
    }

    @GetMapping("/{transactionLogId}")
    public ResponseEntity<TransactionLog> getTransactionLogById(@PathVariable String transactionLogId) {
        TransactionLog transactionLog = transactionLogService.getTransactionLogById(transactionLogId);
        return transactionLog != null ? ResponseEntity.ok(transactionLog) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TransactionLog> createTransactionLog(@RequestBody TransactionLog transactionLog) {
        TransactionLog createdTransactionLog = transactionLogService.createTransactionLog(transactionLog);
        return ResponseEntity.ok(createdTransactionLog);
    }

    @DeleteMapping("/{transactionLogId}")
    public ResponseEntity<Void> deleteTransactionLog(@PathVariable String transactionLogId) {
        transactionLogService.deleteTransactionLog(transactionLogId);
        return ResponseEntity.noContent().build();
    }
}
