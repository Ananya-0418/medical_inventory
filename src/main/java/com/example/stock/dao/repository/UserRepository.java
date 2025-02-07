package com.example.stock.dao.repository;

import com.example.stock.dao.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Custom queries for users can be added here if needed
}
