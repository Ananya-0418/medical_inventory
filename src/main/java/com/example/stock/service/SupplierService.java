package com.example.stock.service;

import com.example.stock.dao.model.Stock;
import com.example.stock.dao.model.Supplier;
import com.example.stock.dao.repository.StockRepository;
import com.example.stock.dao.repository.SupplierItemRepository;
import com.example.stock.dao.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierItemRepository supplieritemRepository;

    @Autowired
    private StockRepository stockRepository;

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get a supplier by ID
    public Supplier getSupplierById(String supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        return supplierOptional.orElse(null);  // Return null if not found
    }

    // Create a new supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> createSuppliers(List<Supplier> suppliers) {
        // Additional logic like validation can be added here
        return supplierRepository.saveAll(suppliers);
    }

    // Update an existing supplier
    public Supplier updateSupplier(String supplierId, Supplier updatedSupplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if (supplierOptional.isPresent()) {
            Supplier existingSupplier = supplierOptional.get();
            existingSupplier.setName(updatedSupplier.getName());
            existingSupplier.setContactNumber(updatedSupplier.getContactNumber());
            existingSupplier.setEmail(updatedSupplier.getEmail());
            existingSupplier.setAddress(updatedSupplier.getAddress());
            existingSupplier.setSuppliedItems(updatedSupplier.getSuppliedItems());
            return supplierRepository.save(existingSupplier);
        }
        return null;  // Supplier not found
    }

    // Delete a supplier
    public void deleteSupplier(String supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    public void buyStockFromSupplier(String itemId, int quantity) {
        // Find suppliers that provide the specific item
        List<Supplier> suppliers = supplierRepository.findBySuppliedItemsItemId(itemId);
        for (Supplier supplier : suppliers) {
            int availableStock = supplier.getItemQuantity(itemId); // Get the available stock for the item
            if (availableStock >= quantity) {
                // Update supplier stock and your inventory
                supplier.decreaseItemQuantity(itemId, quantity); // Decrease stock at the supplier
                supplierRepository.save(supplier); // Save the updated supplier

                // Update your inventory stock
                Stock currentStock = stockRepository.findByItemId(itemId).orElseThrow();
                currentStock.setQuantity(currentStock.getQuantity() + quantity);
                stockRepository.save(currentStock);

                System.out.println("Purchased " + quantity + " " + itemId + " from supplier " + supplier.getName());
                return;
            }
        }

        // If not enough stock, throw an exception
        throw new RuntimeException("Not enough stock available from suppliers.");
    }

}
