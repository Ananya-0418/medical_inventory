package com.example.stock.dao.repository;

import com.example.stock.dao.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findById(String itemId);

    List<Item> findByCategoryId(String categoryId);
}
