package com.example.stock.service.controller;

import com.example.stock.dao.model.Stock;
import com.example.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    // Get all stocks
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    // Get stock by item ID
    @GetMapping("/{itemId}")
    public ResponseEntity<Stock> getStockByItemId(@PathVariable String itemId) {
        Optional<Stock> stock = stockService.getStockByItemId(itemId);
        return stock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Check stock availability for an item
    @GetMapping("/check/{itemId}/{requestedQuantity}")
    public ResponseEntity<String> checkStockAvailability(@PathVariable String itemId, @PathVariable int requestedQuantity) {
        boolean isAvailable = stockService.isStockAvailable(itemId, requestedQuantity);
        if (isAvailable) {
            return ResponseEntity.ok("Stock is available.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Stock is not available.");
        }
    }

    // Correct the casting of the list of stocks
    @PostMapping
    public ResponseEntity<Void> addStocks(@RequestBody List<Stock> stocks) {
        stockService.addStock(stocks);
        return ResponseEntity.ok().build();
    }



    // Update stock quantity (either restocking or after sale)
    @PutMapping("/{itemId}")
    public ResponseEntity<Stock> updateStock(@PathVariable String itemId, @RequestParam int quantityChange) {
        Stock updatedStock = stockService.updateStock(itemId, quantityChange);
        return updatedStock != null
                ? ResponseEntity.ok(updatedStock)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Reduce stock when an item is sold
    @PutMapping("/reduce/{itemId}")
    public ResponseEntity<Stock> reduceStock(@PathVariable String itemId, @RequestParam int quantitySold) {
        try {
            Stock updatedStock = stockService.reduceStock(itemId, quantitySold);
            return ResponseEntity.ok(updatedStock);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Generate inventory report
    @GetMapping("/report")
    public ResponseEntity<String> generateInventoryReport() {
        String report = stockService.generateInventoryReport();
        return ResponseEntity.ok(report);
    }
}
