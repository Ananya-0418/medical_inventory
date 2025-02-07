package com.example.stock.dao.repository;

import com.example.stock.dao.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    @Query("{'suppliedItems.itemId': ?0}")
    List<Supplier> findByItemId(String itemId);


    List<Supplier> findBySuppliedItemsItemId(String itemId);
    // Custom queries for suppliers can be added here if needed
}
