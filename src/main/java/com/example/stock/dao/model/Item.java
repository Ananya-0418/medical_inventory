package com.example.stock.dao.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "items")
public class Item {
    @Id
    private String itemId;
    @Getter
    private String name;
    private String description;
    private String categoryId;
    private double unitPrice;
    private int quantityAvailable;
    private Date expiryDate;

    public Item() {
    }

    public Item(String id, String name, String description, String categoryId, double unitPrice, int quantityAvailable, Date expiryDate) {
        this.itemId = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.quantityAvailable = quantityAvailable;
        this.expiryDate = expiryDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String id) {
        this.itemId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
