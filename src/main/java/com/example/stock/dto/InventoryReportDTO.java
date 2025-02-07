package com.example.stock.dto;

import com.example.stock.dao.model.Shop;
import java.util.List;

public class InventoryReportDTO {

    private String itemName;
    private int requestedQuantity;
    private int availableStock;
    private List<Shop> nearbyShops;

    // Constructor
    public InventoryReportDTO(String itemName, int requestedQuantity, int availableStock, List<Shop> nearbyShops) {
        this.itemName = itemName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
        this.nearbyShops = nearbyShops;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public List<Shop> getNearbyShops() {
        return nearbyShops;
    }

    public void setNearbyShops(List<Shop> nearbyShops) {
        this.nearbyShops = nearbyShops;
    }

    // Optional: Add a method to display report details in a formatted way
    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append("Item: ").append(itemName).append("\n")
                .append("Requested Quantity: ").append(requestedQuantity).append("\n")
                .append("Available Stock: ").append(availableStock).append("\n");

        if (nearbyShops != null && !nearbyShops.isEmpty()) {
            report.append("Nearby Shops: \n");
            for (Shop shop : nearbyShops) {
                report.append("  - Shop: ").append(shop.getName())
                        .append(", Location: ").append(shop.getLocation())
                        .append("\n");
            }
        } else {
            report.append("No nearby shops found.");
        }

        return report.toString();
    }
}
