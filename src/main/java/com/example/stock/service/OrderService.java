package com.example.stock.service;

import com.example.stock.dao.model.Order;
import com.example.stock.dao.model.OrderItem;
import com.example.stock.dao.model.Stock;
import com.example.stock.dao.repository.OrderRepository;
import com.example.stock.dao.repository.ItemRepository;
import com.example.stock.dao.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, StockRepository stockRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
    }

    // Create multiple orders at once
    public List<Order> createOrders(List<Order> orders) {
        // Check stock availability before saving orders
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                Stock stock = stockRepository.findByItemId(orderItem.getItemId())
                        .orElseThrow(() -> new RuntimeException("Stock not found for item: " + orderItem.getItemId()));

                // Reduce stock quantity if available
                if (stock.getQuantity() >= orderItem.getQuantity()) {
                    stock.setQuantity(stock.getQuantity() - orderItem.getQuantity());
                    stockRepository.save(stock);
                } else {
                    throw new RuntimeException("Not enough stock available for item: " + orderItem.getItemId());
                }
            }

            // Set the order date and total amount
            order.setOrderDate(new Date());
            order.setTotalAmount(order.getOrderItems().stream()
                    .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                    .sum());
        }

        return orderRepository.saveAll(orders);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    // Update order status
    public Order updateOrderStatus(String id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    // Delete order by ID
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        orderRepository.delete(order);
    }
}
