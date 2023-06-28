package com.pizza.MELT.controller;

import com.pizza.MELT.pojo.Order;
import com.pizza.MELT.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    @Autowired
    private final OrderServiceImpl orderServiceImpl;


    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        try {
            Order newOrder = orderServiceImpl.createNewOrder(order);
           return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Order());
       }
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderServiceImpl.findAllOrders();
    }
}
