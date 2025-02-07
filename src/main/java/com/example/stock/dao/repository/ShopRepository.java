package com.example.stock.dao.repository;

import com.example.stock.dao.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
    List<Shop> findByItems_ItemId(String itemId);
    // Custom queries for shops can be added here if needed
}
