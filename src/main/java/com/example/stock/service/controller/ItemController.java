package com.example.stock.service.controller;

import com.example.stock.dao.model.Item;
import com.example.stock.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) {
        List<Item> createdItems = itemService.createItems(items);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItems);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }
}
