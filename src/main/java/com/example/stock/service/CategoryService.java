package com.example.stock.service;

import com.example.stock.dao.model.Category;
import com.example.stock.dao.model.Item;
import com.example.stock.dao.model.Stock;
import com.example.stock.dao.repository.CategoryRepository;
import com.example.stock.dao.repository.ItemRepository;
import com.example.stock.dao.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    // Fetch all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Fetch category by categoryid
    public Optional<Category> getCategoryByCategoryId(String categoryid) {
        return categoryRepository.findByCategoryid(categoryid);
    }

    // Create a single category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Create multiple categories
    public List<Category> createCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    // Update category by categoryid
    public Category updateCategory(String categoryid, Category categoryDetails) {
        Category category = categoryRepository.findByCategoryid(categoryid)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    // Delete category by categoryid
    public void deleteCategory(String categoryid) {
        Optional<Category> category = categoryRepository.findByCategoryid(categoryid);
        category.ifPresent(value -> categoryRepository.delete(value));
    }

    // Fetch items by categoryid
    public List<Item> getItemsByCategory(String categoryid) {
        return itemRepository.findByCategoryId(categoryid);
    }

    // Generate inventory report based on categoryid and requested quantity
    public Map<String, Object> generateInventoryReport(String categoryid, int quantityRequested) {
        Map<String, Object> report = new HashMap<>();

        // Step 1: Get items by category
        List<Item> items = itemRepository.findByCategoryId(categoryid);

        // Step 2: Check each item availability and quantity
        for (Item item : items) {
            Map<String, Object> itemReport = new HashMap<>();
            itemReport.put("Item Name", item.getName());
            itemReport.put("Requested Quantity", quantityRequested);

            // Step 3: Check stock availability
            Optional<Stock> stock = stockRepository.findByItemId(item.getItemId());
            if (stock.isPresent() && stock.get().getQuantity() >= quantityRequested) {
                itemReport.put("Available in Inventory", "Yes");
                itemReport.put("Remaining Quantity", stock.get().getQuantity() - quantityRequested);
            } else {
                itemReport.put("Available in Inventory", "No");
            }

            report.put(item.getName(), itemReport);
        }

        return report;
    }
}
