package com.pizza.MELT.service;

import com.pizza.MELT.pojo.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrders();

    Order createOrder(Order order);

    void updateOrder(Order order);

    void  deleteOrder(Long id);

    Optional<Order> findByName(String name);


}
