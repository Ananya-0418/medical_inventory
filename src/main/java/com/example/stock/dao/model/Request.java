package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "requests")
public class Request {
    @Id
    private String id;
    private String userId; // Customer who requested the item
    private String itemId;
    private int quantityRequested;
    private String status; // Pending, Fulfilled, Not Available
    private Date requestDate;
    private String orderId; // If fulfilled, link to the order

    public Request() {}

    public Request(String userId, String itemId, int quantityRequested, String status, Date requestDate) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantityRequested = quantityRequested;
        this.status = status;
        this.requestDate = requestDate;
    }

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
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public int getQuantityRequested() {
        return quantityRequested;
    }
    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    }
