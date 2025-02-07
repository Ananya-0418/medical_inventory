package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String itemId;
    private int quantity;
    private Date lastUpdated;

    public Stock() {}

    public Stock(String id, String itemId, int quantity, Date lastUpdated) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public Stock(String itemId, int i, Date date) {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
