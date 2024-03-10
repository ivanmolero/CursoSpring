package com.imolerodev.pizza.web.controller;

import com.imolerodev.pizza.persistence.entity.CustomerEntity;
import com.imolerodev.pizza.persistence.entity.OrderEntity;
import com.imolerodev.pizza.service.CustomerService;
import com.imolerodev.pizza.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        return new ResponseEntity<>(this.customerService.findByPhone(phone), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable("id") String idCustomer) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }
}
