package com.example.stock.dao.repository;

import com.example.stock.dao.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    // Custom queries for order items can be added here if needed
}
