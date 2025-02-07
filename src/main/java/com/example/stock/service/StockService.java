package com.example.stock.service;

import com.example.stock.dao.model.Stock;
import com.example.stock.dao.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Get all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Get stock by item ID
    public Optional<Stock> getStockByItemId(String itemId) {
        return stockRepository.findByItemId(itemId);
    }

    // Check if the stock is available for an item
    public boolean isStockAvailable(String itemId, int requestedQuantity) {
        Optional<Stock> stock = stockRepository.findByItemId(itemId);
        return stock.isPresent() && stock.get().getQuantity() >= requestedQuantity;
    }

    // Add stock to inventory
    public void addStock(List<Stock> stocks) {
        stockRepository.saveAll(stocks);  // Persist the stocks to the database
    }

    // Update stock quantity after a sale or restocking
    public Stock updateStock(String itemId, int quantityChange) {
        Optional<Stock> stock = stockRepository.findByItemId(itemId);
        if (stock.isPresent()) {
            Stock existingStock = stock.get();
            existingStock.setQuantity(existingStock.getQuantity() + quantityChange);
            existingStock.setLastUpdated(new Date());
            return stockRepository.save(existingStock);
        }
        return null; // Or throw a custom exception
    }

    // Remove stock from inventory (when an item is sold)
    public Stock reduceStock(String itemId, int quantitySold) {
        Optional<Stock> stock = stockRepository.findByItemId(itemId);
        if (stock.isPresent()) {
            Stock existingStock = stock.get();
            int remainingQuantity = existingStock.getQuantity() - quantitySold;
            if (remainingQuantity >= 0) {
                existingStock.setQuantity(remainingQuantity);
                existingStock.setLastUpdated(new Date());
                return stockRepository.save(existingStock);
            } else {
                throw new IllegalArgumentException("Insufficient stock");
            }
        }
        return null;
    }



    // Generate inventory report
    public String generateInventoryReport() {
        List<Stock> stocks = stockRepository.findAll();
        StringBuilder report = new StringBuilder("Inventory Report\n\n");
        for (Stock stock : stocks) {
            report.append("Item ID: ").append(stock.getItemId())
                    .append(", Quantity: ").append(stock.getQuantity())
                    .append(", Last Updated: ").append(stock.getLastUpdated()).append("\n");
        }
        return report.toString();
    }
}
