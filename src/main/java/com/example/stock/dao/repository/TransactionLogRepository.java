package com.example.stock.dao.repository;

import com.example.stock.dao.model.TransactionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends MongoRepository<TransactionLog, String> {
    // Custom queries for transaction logs can be added here if needed
}
