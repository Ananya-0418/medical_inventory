package com.example.stock.dao.repository;

import com.example.stock.dao.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<Stock, String> {

    Optional<Stock> findByItemId(String itemId);
}
