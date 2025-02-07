package com.example.stock.dto;

import com.example.stock.dao.model.OrderItem;

import java.util.List;

public class OrderDTO {

    private String userId;
    private List<OrderItem> orderItems;
    private double totalAmount;
    private String status;
    private String supplierId;
    private String orderDate;
    private String deliveryDate;

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
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
