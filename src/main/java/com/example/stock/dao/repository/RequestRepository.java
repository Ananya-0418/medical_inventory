package com.example.stock.dao.repository;

import com.example.stock.dao.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {
    List<Request> findByStatus(String status);
    // Custom queries for requests can be added here if needed
}
