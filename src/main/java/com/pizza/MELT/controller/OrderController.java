package com.pizza.MELT.controller;

import com.pizza.MELT.pojo.Order;
import com.pizza.MELT.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class OrderController {
    @Autowired
    private final OrderServiceImpl orderServiceImpl;



    @GetMapping("/")
    public Order dumbData(Order order){
        Order order1 = new Order();
        order1.setId(order.getId());
        order1.setName("Joanthony");
        order1.setPizza("Cheese");
        order1.setQuantity(4);
        order1.setPhoneNumber("123456789");
        order1.setAddress("345 East Hampton Dr");

        return order1;
    }

    @PostMapping("/create")
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

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        try {
            orderServiceImpl.updateOrder(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
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