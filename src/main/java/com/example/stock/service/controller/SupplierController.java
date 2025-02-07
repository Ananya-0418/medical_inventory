package com.example.stock.service.controller;

import com.example.stock.dao.model.Supplier;
import com.example.stock.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Get all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Get a supplier by ID
    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable String supplierId) {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<List<Supplier>> createSuppliers(@RequestBody List<Supplier> suppliers) {
        List<Supplier> createdSuppliers = supplierService.createSuppliers(suppliers);
        return ResponseEntity.ok(createdSuppliers);
    }


    // Update an existing supplier
    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable String supplierId, @RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(supplierId, supplier);
        return updatedSupplier != null ? ResponseEntity.ok(updatedSupplier) : ResponseEntity.notFound().build();
    }

    // Delete a supplier
    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String supplierId) {
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
