package com.example.stock.dto;

import java.util.Date;

public class ItemDTO {
    private String id;
    private String name;
    private String categoryId;
    private double unitPrice;
    private int quantityAvailable;
    private Date expiryDate;

    // Constructor, Getters, and Setters
    public ItemDTO(String id, String name, String categoryId, double unitPrice, int quantityAvailable, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.quantityAvailable = quantityAvailable;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
