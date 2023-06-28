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
    public Order createNewOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setName(order.getName());
        newOrder.setPhoneNumber(order.getPhoneNumber());
        newOrder.setAddress(order.getAddress());
        newOrder.setPizza(order.getPizza());
        newOrder.setQuantity(order.getQuantity());

        return orderRepository.save(newOrder);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findByName(String name) { //Optional container  allows handles scenarios where values may or may not be present
        List<Order> records = orderRepository.findAll();
        Optional<Order> nameOnOrder = records.stream()
                .filter(order -> order.getName().equals(name))
                .findFirst();

        if(nameOnOrder.isPresent()){
            return nameOnOrder;
        } else {
            System.out.println("Nam not found");
            return Optional.empty();
        }
    }
}