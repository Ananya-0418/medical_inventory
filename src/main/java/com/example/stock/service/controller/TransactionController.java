package com.example.stock.service.controller;

import com.example.stock.dao.model.Stock;
import com.example.stock.dto.TransactionResponseDTO;
import com.example.stock.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/buy/{itemId}")
    public ResponseEntity<Stock> buyStock(@PathVariable String itemId, @RequestParam int quantity) {
        try {
            Stock updatedStock = transactionService.buyStock(itemId, quantity);
            return ResponseEntity.ok(updatedStock);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

//    @PutMapping("/sell/{itemId}")
//    public ResponseEntity<String> sellStock(@PathVariable String itemId, @RequestParam int quantity) {
//        try {
//            // Check if the stock is available before proceeding with the sale
//            Stock updatedStock = transactionService.sellStock(itemId, quantity);
//
//            // Get the updated stock quantity from the Stock object
//            int remainingQuantity = updatedStock.getQuantity();
//
//            // Return a message with the updated stock quantity
//            return ResponseEntity.ok("Sale successful! Updated stock quantity: " + remainingQuantity);
//        } catch (IllegalArgumentException e) {
//            // Log a message indicating we're reaching out to suppliers
//            System.out.println("Insufficient stock for item: " + itemId + ". Reaching out to suppliers...");
//
//            // Return a message to the client indicating we're attempting to restock
//            return ResponseEntity.status(400)
//                    .body("Insufficient stock. Reaching out to suppliers to restock item: " + itemId);
//        }
//    }
@PostMapping("/sell/{itemId}")
public ResponseEntity<TransactionResponseDTO> sellStock(@PathVariable String itemId, @RequestParam int quantity) {
    return transactionService.sellStock(itemId, quantity);
}
}
