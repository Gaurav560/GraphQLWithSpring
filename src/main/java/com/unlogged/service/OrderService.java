package com.unlogged.service;

import com.unlogged.entity.CustomerOrder;
import com.unlogged.repo.CustomerOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final CustomerOrderRepo orderRepo;

    @Autowired
    public OrderService(CustomerOrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    // Create a new order
    public CustomerOrder createOrder(CustomerOrder order) {
        return orderRepo.save(order);
    }

    // Read an order by ID
    public Optional<CustomerOrder> getOrderById(int orderId) {
        return orderRepo.findById(orderId);
    }

    // Read all orders
    public List<CustomerOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    // Update an order
    public CustomerOrder updateOrder(CustomerOrder order) {
        if (orderRepo.existsById(order.getOrderId())) {
            return orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("Order not found with id: " + order.getOrderId());
        }
    }

    // Delete an order by ID
    public boolean deleteOrderById(int orderId) {
        if (orderRepo.existsById(orderId)) {
            orderRepo.deleteById(orderId);
            return true;
        }
        return false;
    }
}
