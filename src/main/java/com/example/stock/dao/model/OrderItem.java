package com.example.stock.dao.model;

public class OrderItem {

    private String itemId;  // The ID of the item in the order
    private String itemName;  // The name of the item
    private int quantity;  // Quantity of the item ordered
    private double unitPrice;  // Price of a single unit of the item
    private double totalPrice;  // Total price of this item (unitPrice * quantity)

    public OrderItem() {}

    public OrderItem(String itemId, String itemName, int quantity, double unitPrice, double totalPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
