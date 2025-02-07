package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;  // Unique identifier for the order
    private String userId;  // Refers to the customer who placed the order
    private List<OrderItem> orderItems;  // List of items ordered
    private double totalAmount;  // Total price of the order
    private String status;  // Order status (e.g., "Pending", "Completed", "Cancelled")
    private String supplierId;  // The supplier who provided the item if not available in inventory
    private Date orderDate;  // Date when the order was created
    private Date deliveryDate;  // Date when the order was delivered or expected to be delivered

    public Order() {}

    public Order(String userId, List<OrderItem> orderItems, double totalAmount, String status, String supplierId, Date orderDate, Date deliveryDate) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.status = status;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}

