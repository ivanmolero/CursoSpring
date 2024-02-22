package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.OrderEntity;
import com.imolerodev.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(orderEntity -> {
            System.out.println(orderEntity.getCustomer().getName());
        });
        return orders;
    }
}
