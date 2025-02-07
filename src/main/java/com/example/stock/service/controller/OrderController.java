package com.example.stock.service.controller;

import com.example.stock.dao.model.Order;
import com.example.stock.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create multiple orders
    @PostMapping
    public ResponseEntity<List<Order>> createOrders(@RequestBody List<Order> orders) {
        List<Order> createdOrders = orderService.createOrders(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrders);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Update order status
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @RequestBody String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
