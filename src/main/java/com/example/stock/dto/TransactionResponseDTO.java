package com.example.stock.dto;

public class TransactionResponseDTO {
    private String message;
    private int updatedStock;

    // Constructor
    public TransactionResponseDTO(String message, int updatedStock) {
        this.message = message;
        this.updatedStock = updatedStock;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUpdatedStock() {
        return updatedStock;
    }

    public void setUpdatedStock(int updatedStock) {
        this.updatedStock = updatedStock;
    }
}
