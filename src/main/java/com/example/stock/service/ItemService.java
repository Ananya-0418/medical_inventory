package com.example.stock.service;

import com.example.stock.dao.model.Item;
import com.example.stock.dao.model.Stock;
import com.example.stock.dao.repository.ItemRepository;
import com.example.stock.dao.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    public String checkItemAvailability(String itemId, int requestedQuantity) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (!itemOpt.isPresent()) {
            return "Item not found.";
        }
        Item item = itemOpt.get();

        Optional<Stock> stockOpt = stockRepository.findByItemId(itemId);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            if (stock.getQuantity() >= requestedQuantity) {
                return "Item is available. Remaining stock: " + (stock.getQuantity() - requestedQuantity);
            } else {
                return "Insufficient stock. Available: " + stock.getQuantity();
            }
        }
        return "No stock found for the item.";
    }
    public List<Item> createItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new main.java.com.example.stock.exception.ItemNotFoundException(id));
    }
    // Update stock after a sale or purchase
    public String updateStock(String itemId, int soldQuantity) {
        Optional<Stock> stockOpt = stockRepository.findByItemId(itemId);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            if (stock.getQuantity() >= soldQuantity) {
                stock.setQuantity(stock.getQuantity() - soldQuantity);
                stockRepository.save(stock);
                return "Stock updated. Remaining stock: " + stock.getQuantity();
            } else {
                return "Not enough stock to update.";
            }
        }
        return "Item not found in inventory.";
    }
}
