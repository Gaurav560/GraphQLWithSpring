package com.unlogged.controller;

import com.unlogged.entity.CustomerOrder;
import com.unlogged.entity.User;
import com.unlogged.service.OrderService;
import com.unlogged.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private UserService userService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    private OrderService orderService;


    //create order
    @MutationMapping
    public CustomerOrder createOrder(@Argument String orderDetail, @Argument Integer price, @Argument int userId) {
        Optional<User> user = userService.getUserById(userId);

        CustomerOrder order = new CustomerOrder();
        order.setOrderDetail(orderDetail);
        order.setPrice(price);
        order.setUser(user.get());
        return orderService.createOrder(order);
    }

    //get order by id
    @QueryMapping
    public CustomerOrder getOrderById(@Argument int orderId) {
        return orderService.getOrderById(orderId).get();
    }


    //get orders
    @QueryMapping
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @MutationMapping
    public boolean deleteOrderById(@Argument int orderId) {
        return orderService.deleteOrderById(orderId);
    }
}
