package com.example.stock.service.controller;

import com.example.stock.dao.model.Shop;
import com.example.stock.dao.model.Item;
import com.example.stock.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<Shop> getShopById(@PathVariable String shopId) {
        Shop shop = shopService.getShopById(shopId);
        return shop != null ? ResponseEntity.ok(shop) : ResponseEntity.notFound().build();
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Shop>> getShopsWithItem(@PathVariable String itemId) {
        List<Shop> shops = shopService.getShopsWithItem(itemId);
        return ResponseEntity.ok(shops);
    }

    @PutMapping("/{shopId}/addItems")
    public ResponseEntity<Shop> addItemsToShop(@PathVariable String shopId, @RequestBody List<Item> items) {
        Shop updatedShop = shopService.addItemsToShop(shopId, items);
        return updatedShop != null ? ResponseEntity.ok(updatedShop) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<List<Shop>> saveShops(@RequestBody List<Shop> shops) {
        List<Shop> savedShops = shopService.saveShops(shops);
        return ResponseEntity.ok(savedShops);
    }


    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable String shopId) {
        shopService.deleteShop(shopId);
        return ResponseEntity.noContent().build();
    }
}
