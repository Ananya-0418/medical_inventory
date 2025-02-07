package com.example.stock.service.controller;

import com.example.stock.dao.model.Category;
import com.example.stock.dao.model.Item;
import com.example.stock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get a category by categoryid
    @GetMapping("/{categoryid}")
    public ResponseEntity<Category> getCategoryByCategoryId(@PathVariable String categoryid) {
        Optional<Category> category = categoryService.getCategoryByCategoryId(categoryid);
        return category.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Create multiple categories
    @PostMapping("/bulk")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
        List<Category> savedCategories = categoryService.createCategories(categories);
        return new ResponseEntity<>(savedCategories, HttpStatus.CREATED);
    }

    // Update category by categoryid
    @PutMapping("/{categoryid}")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryid, @RequestBody Category categoryDetails) {
        Category updatedCategory = categoryService.updateCategory(categoryid, categoryDetails);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Delete category by categoryid
    @DeleteMapping("/{categoryid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryid) {
        categoryService.deleteCategory(categoryid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get items by categoryid
    @GetMapping("/{categoryid}/items")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String categoryid) {
        List<Item> items = categoryService.getItemsByCategory(categoryid);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping("/{categoryid}/inventory-report")
    public ResponseEntity<Map<String, Object>> generateInventoryReport(
            @PathVariable String categoryid,
            @RequestParam int quantityRequested) {

        Map<String, Object> report = categoryService.generateInventoryReport(categoryid, quantityRequested);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
