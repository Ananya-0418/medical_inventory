package com.example.stock.dto;

import java.util.List;

public class SupplierDTO {
    private String id;
    private String name;
    private List<String> suppliedItems;

    // Constructor, Getters, and Setters
    public SupplierDTO(String id, String name, List<String> suppliedItems) {
        this.id = id;
        this.name = name;
        this.suppliedItems = suppliedItems;
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

    public List<String> getSuppliedItems() {
        return suppliedItems;
    }

    public void setSuppliedItems(List<String> suppliedItems) {
        this.suppliedItems = suppliedItems;
    }
}
