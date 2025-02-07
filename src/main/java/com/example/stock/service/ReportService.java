package com.example.stock.service;

import com.example.stock.dao.model.*;
import com.example.stock.dao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ReportService {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TransactionLogRepository transactionLogRepository;

    public List<Map<String, Object>> generateConsolidatedReport() {
        List<Request> requests = requestRepository.findAll();
        List<Map<String, Object>> report = new ArrayList<>();

        for (Request request : requests) {
            Map<String, Object> requestReport = new HashMap<>();
            String itemId = request.getItemId();
            int quantityRequested = request.getQuantityRequested();

            // Step 1: Check if the item is available in inventory
            Optional<Stock> optionalStock = stockRepository.findByItemId(itemId);
            Stock stock = optionalStock.orElse(null);  // If the item is not found, stock will be null
            if (stock != null && stock.getQuantity() >= quantityRequested) {
                requestReport.put("Item", Objects.requireNonNull(itemRepository.findById(itemId).orElse(null)).getName());
                requestReport.put("Requested Quantity", quantityRequested);
                requestReport.put("Available in Inventory", "Yes");
                requestReport.put("Remaining Quantity", stock.getQuantity() - quantityRequested);

                // Update stock in inventory after sale
                stock.setQuantity(stock.getQuantity() - quantityRequested);
                stockRepository.save(stock);

                // Step 4: Log the transaction
                TransactionLog transaction = new TransactionLog(itemId, quantityRequested, "Inventory", new Date(), null);
                transactionLogRepository.save(transaction);

            } else {
                // Step 2: Check if the item is available in nearby shops
                List<Shop> nearbyShops = shopRepository.findAll();
                List<Shop> availableShops = nearbyShops.stream()
                        .filter(shop -> shop.getItems().stream().anyMatch(item -> item.getItemId().equals(itemId) && item.getQuantityAvailable() >= quantityRequested))
                        .collect(Collectors.toList());

                requestReport.put("Available in Inventory", "No");
                requestReport.put("Nearby Shops", availableShops);

                // Step 3: Get the item list from shops
                List<Map<String, Object>> shopsReport = new ArrayList<>();
                for (Shop shop : availableShops) {
                    Map<String, Object> shopReport = new HashMap<>();
                    shopReport.put("Shop Name", shop.getName());
                    shopReport.put("Location", shop.getLocation());
                    shopReport.put("Items Available", shop.getItems().stream().map(Item::getName).collect(Collectors.toList()));
                    shopReport.put("Requested Item Quantity", shop.getItems().stream().filter(item -> item.getItemId().equals(itemId)).findFirst().get().getQuantityAvailable());
                    shopsReport.add(shopReport);
                }
                requestReport.put("Shops with Item", shopsReport);

                // Simulate purchase from a shop
                if (!availableShops.isEmpty()) {
                    Shop shopToPurchaseFrom = availableShops.get(0); // For simplicity, take the first available shop
                    shopToPurchaseFrom.getItems().forEach(item -> {
                        if (item.getItemId().equals(itemId)) {
                            item.setQuantityAvailable(item.getQuantityAvailable() - quantityRequested); // Corrected line
                        }
                    });
                    shopRepository.save(shopToPurchaseFrom);

                    // Update inventory after purchase from shop
                    Stock newStock = stockRepository.findByItemId(itemId).orElse(new Stock(itemId, 0, new Date())); // Create new stock if not found
                    newStock.setQuantity(newStock.getQuantity() + quantityRequested);
                    stockRepository.save(newStock);

                    // Step 4: Log the transaction
                    TransactionLog transaction = new TransactionLog(itemId, quantityRequested, shopToPurchaseFrom.getId(), new Date(), shopToPurchaseFrom.getId());
                    transactionLogRepository.save(transaction);
                }
            }

            // Final stock levels after sale
            requestReport.put("Final Stock Level After Sale", stock != null ? stock.getQuantity() : 0);
            report.add(requestReport);
        }

        return report;
    }

}
