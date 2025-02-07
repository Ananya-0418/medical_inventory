package com.example.stock.service;

import com.example.stock.dao.model.Shop;
import com.example.stock.dao.model.Item;
import com.example.stock.dao.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // Get all shops
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get a shop by ID
    public Shop getShopById(String shopId) {
        Optional<Shop> shopOptional = shopRepository.findById(shopId);
        return shopOptional.orElse(null);  // Return null if shop not found
    }

    // Get shops with available item
    public List<Shop> getShopsWithItem(String itemId) {
        return shopRepository.findByItems_ItemId(itemId);
    }

    // Add items to a shop's inventory
    public Shop addItemsToShop(String shopId, List<Item> items) {
        Optional<Shop> shopOptional = shopRepository.findById(shopId);
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();
            shop.getItems().addAll(items);  // Add new items to the existing list of items
            return shopRepository.save(shop);
        }
        return null;  // Shop not found
    }

    // Create or update a shop
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public List<Shop> saveShops(List<Shop> shops) {
        // Assuming you have a repository to save the shops
        return shopRepository.saveAll(shops);
    }

    // Delete a shop
    public void deleteShop(String shopId) {
        shopRepository.deleteById(shopId);
    }
}
