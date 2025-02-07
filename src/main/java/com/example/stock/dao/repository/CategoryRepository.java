package com.example.stock.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.stock.dao.model.Category;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByCategoryid(String categoryid);
}
