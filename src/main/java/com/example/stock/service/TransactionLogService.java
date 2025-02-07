package com.example.stock.service;

import com.example.stock.dao.model.TransactionLog;
import com.example.stock.dao.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionLogService {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    // Get all transaction logs
    public List<TransactionLog> getAllTransactionLogs() {
        return transactionLogRepository.findAll();
    }

    // Get a transaction log by ID
    public TransactionLog getTransactionLogById(String transactionLogId) {
        Optional<TransactionLog> transactionLogOptional = transactionLogRepository.findById(transactionLogId);
        return transactionLogOptional.orElse(null); // Return null if not found
    }

    // Create a new transaction log
    public TransactionLog createTransactionLog(TransactionLog transactionLog) {
        return transactionLogRepository.save(transactionLog);
    }

    // Delete a transaction log
    public void deleteTransactionLog(String transactionLogId) {
        transactionLogRepository.deleteById(transactionLogId);
    }
}
