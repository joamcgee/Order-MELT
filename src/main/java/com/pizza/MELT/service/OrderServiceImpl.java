package com.pizza.MELT.service;

import com.pizza.MELT.pojo.Order;
import com.pizza.MELT.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setName(order.getName());
        newOrder.setPhoneNumber(order.getPhoneNumber());
        newOrder.setAddress(order.getAddress());
        newOrder.setPizza(order.getPizza());
        newOrder.setQuantity(order.getQuantity());

        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findByName(String name) { //Optional container handles scenarios where values may or may not be present
     List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .filter(order -> order.getName().equals(name))
                .findFirst();
    }
}































