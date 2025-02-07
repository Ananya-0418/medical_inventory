package com.example.stock.service;

import com.example.stock.dao.model.Stock;
import com.example.stock.dao.model.Order;
import com.example.stock.dao.repository.StockRepository;
import com.example.stock.dto.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

//@Service
//public class TransactionService {
//
//    @Autowired
//    private StockRepository stockRepository;
//
//    // Buying (Restocking from supplier)
//    public Stock buyStock(String itemId, int quantity) {
//        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
//        if (stockOptional.isPresent()) {
//            Stock existingStock = stockOptional.get();
//            existingStock.setQuantity(existingStock.getQuantity() + quantity);
//            existingStock.setLastUpdated(new Date());
//            return stockRepository.save(existingStock);
//        } else {
//            // If no stock exists for the item, create a new entry
//            Stock newStock = new Stock(null, itemId, quantity, new Date());
//            return stockRepository.save(newStock);
//        }
//    }
//
//    // Selling (Reducing stock when an order is placed)
//    public Stock sellStock(String itemId, int quantity) {
//        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
//        if (stockOptional.isPresent()) {
//            Stock existingStock = stockOptional.get();
//            int remainingQuantity = existingStock.getQuantity() - quantity;
//            if (remainingQuantity >= 0) {
//                existingStock.setQuantity(remainingQuantity);
//                existingStock.setLastUpdated(new Date());
//                return stockRepository.save(existingStock);
//            } else {
//                throw new IllegalArgumentException("Insufficient stock to complete the sale.");
//            }
//        }
//        throw new IllegalArgumentException("Item not found in inventory.");
//    }
//}

//@Service
//public class TransactionService {
//
//    @Autowired
//    private StockRepository stockRepository;
//
//    @Autowired
//    private SupplierService supplierService; // Service to interact with suppliers
//
//    // Selling (Reducing stock when an order is placed)
//    public Stock sellStock(String itemId, int quantity) {
//        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
//        if (stockOptional.isPresent()) {
//            Stock existingStock = stockOptional.get();
//            int remainingQuantity = existingStock.getQuantity() - quantity;
//
//            if (remainingQuantity >= 0) {
//                // Update stock if sufficient quantity available
//                existingStock.setQuantity(remainingQuantity);
//                existingStock.setLastUpdated(new Date());
//                return stockRepository.save(existingStock);
//            } else {
//                // Insufficient stock, attempt to restock from suppliers
//                int shortage = Math.abs(remainingQuantity);
//                restockFromSuppliers(itemId, shortage); // Call method to reach suppliers and buy stock
//                // After restocking, try to sell again
//                return sellStock(itemId, quantity);
//            }
//        }
//        throw new IllegalArgumentException("Item not found in inventory.");
//    }
//
//    public Stock buyStock(String itemId, int quantity) {
//        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
//        if (stockOptional.isPresent()) {
//            Stock existingStock = stockOptional.get();
//            existingStock.setQuantity(existingStock.getQuantity() + quantity);
//            existingStock.setLastUpdated(new Date());
//            return stockRepository.save(existingStock);
//        } else {
//            // If no stock exists for the item, create a new entry
//            Stock newStock = new Stock(null, itemId, quantity, new Date());
//            return stockRepository.save(newStock);
//        }
//    }
//
//    // Method to restock items from suppliers when stock is insufficient
//    private void restockFromSuppliers(String itemId, int shortage) {
//        // Call supplier service to buy from suppliers
//        try {
//            // Example of reaching out to supplier to restock
//            supplierService.buyStockFromSupplier(itemId, shortage);
//            System.out.println("Reached out to suppliers to restock item: " + itemId + " with quantity: " + shortage);
//        } catch (Exception e) {
//            System.out.println("Error while reaching out to suppliers for item: " + itemId);
//            throw new RuntimeException("Unable to restock from suppliers for item: " + itemId);
//        }
//    }
//
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SupplierService supplierService;

    // Selling stock
    public ResponseEntity<TransactionResponseDTO> sellStock(String itemId, int quantity) {
        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
        if (stockOptional.isPresent()) {
            Stock existingStock = stockOptional.get();
            int remainingQuantity = existingStock.getQuantity() - quantity;

            if (remainingQuantity >= 0) {
                // Sufficient stock available, update stock
                existingStock.setQuantity(remainingQuantity);
                existingStock.setLastUpdated(new Date());
                stockRepository.save(existingStock);

                return ResponseEntity.ok(new TransactionResponseDTO("Sale successful! Updated stock quantity: " + remainingQuantity, remainingQuantity));
            } else {
                // Not enough stock, attempt to restock from suppliers
                int shortage = Math.abs(remainingQuantity);
                try {
                    supplierService.buyStockFromSupplier(itemId, shortage);
                    System.out.println("Purchased " + shortage + " " + itemId + " from supplier.");

                    // After restocking, update stock
                    existingStock.setQuantity(existingStock.getQuantity() + shortage);
                    existingStock.setLastUpdated(new Date());
                    stockRepository.save(existingStock);

                    return ResponseEntity.ok(new TransactionResponseDTO("Restocked item: " + itemId + " with quantity: " + shortage + ". Sale successful!", existingStock.getQuantity()));
                } catch (Exception e) {
                    return ResponseEntity.status(500).body(new TransactionResponseDTO("Error while restocking: " + e.getMessage(), existingStock.getQuantity()));
                }
            }
        }
        return ResponseEntity.status(404).body(new TransactionResponseDTO("Item not found in inventory.", 0));
    }

    public Stock buyStock(String itemId, int quantity) {
        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
        if (stockOptional.isPresent()) {
            Stock existingStock = stockOptional.get();
            existingStock.setQuantity(existingStock.getQuantity() + quantity);
            existingStock.setLastUpdated(new Date());
            return stockRepository.save(existingStock);
        } else {
            // If no stock exists for the item, create a new entry
            Stock newStock = new Stock(null, itemId, quantity, new Date());
            return stockRepository.save(newStock);
        }
    }

}

