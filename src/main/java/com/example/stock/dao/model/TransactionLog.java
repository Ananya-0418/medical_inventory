package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactionLogs")
public class TransactionLog {
    @Id
    private String id;
    private String itemId;
    private int quantityPurchased;
    private String supplierId;
    private Date purchaseDate;
    private String shopId;

    public TransactionLog() {}

    public TransactionLog(String itemId, int quantityPurchased, String supplierId, Date purchaseDate, String shopId) {
        this.itemId = itemId;
        this.quantityPurchased = quantityPurchased;
        this.supplierId = supplierId;
        this.purchaseDate = purchaseDate;
        this.shopId = shopId;
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
    public int getQuantityPurchased() {
        return quantityPurchased;
    }
    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
