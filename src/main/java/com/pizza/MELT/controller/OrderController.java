package com.pizza.MELT.controller;

import com.pizza.MELT.pojo.Order;
import com.pizza.MELT.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    @Autowired
    private final OrderServiceImpl orderServiceImpl;


    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order newOrder = orderServiceImpl.createOrder(order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Order());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> findAllOrders() {
        try {
            List<Order> orderList = orderServiceImpl.findAllOrders();
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id ,@RequestBody Order order) {
        try {
            order.setId(id);
            return new ResponseEntity<>(orderServiceImpl.updateOrder(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {
        orderServiceImpl.deleteOrder(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Optional<Order>> findByName(@RequestParam String name) {
        try {
            Optional<Order> response = orderServiceImpl.findByName(name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}