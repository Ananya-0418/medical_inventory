package com.example.stock.dto;

import java.util.Date;

public class StockDTO {
    private String itemId;
    private int quantity;
    private Date lastUpdated;

    // Constructor, Getters, and Setters
    public StockDTO(String itemId, int quantity, Date lastUpdated) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
