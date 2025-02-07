package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "suppliers")
public class Supplier {
    @Id
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private String address;
    private List<SupplierItem> suppliedItems;

    public Supplier() {}

    public Supplier(String id, String name, String contactNumber, String email, String address, List<SupplierItem> suppliedItems) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
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
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<SupplierItem> getSuppliedItems() {
        return suppliedItems;
    }
    public void setSuppliedItems(List<SupplierItem> suppliedItems) {
        this.suppliedItems = suppliedItems;
    }

    // Get quantity of a specific item from the supplier's supplied items
    public int getItemQuantity(String itemId) {
        for (SupplierItem item : suppliedItems) {
            if (item.getItemId().equals(itemId)) {
                return item.getQuantity(); // Return the quantity for the item
            }
        }
        return 0; // Return 0 if the item is not found
    }

    // Decrease the quantity of a specific item
    public void decreaseItemQuantity(String itemId, int quantity) {
        for (SupplierItem item : suppliedItems) {
            if (item.getItemId().equals(itemId)) {
                if (item.getQuantity() >= quantity) {
                    item.setQuantity(item.getQuantity() - quantity); // Reduce quantity
                } else {
                    throw new IllegalArgumentException("Not enough stock available from the supplier.");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Item not found in supplier inventory.");
    }
}
