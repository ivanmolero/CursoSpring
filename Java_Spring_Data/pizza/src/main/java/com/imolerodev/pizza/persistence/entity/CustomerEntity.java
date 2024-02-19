package com.imolerodev.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @Column(length = 15)
    private String idCustomer;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;
}
