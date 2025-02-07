package com.example.stock.dao.repository;

import com.example.stock.dao.model.SupplierItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierItemRepository extends MongoRepository<SupplierItem, String> {
    // Custom queries for supplier items can be added here if needed
}
