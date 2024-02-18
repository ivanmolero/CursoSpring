package com.imolerodev.pizza.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pizza_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @Column(nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "Decimal(6, 2)")
    private Double price;

    @Column(nullable = false, columnDefinition = "Char(1)")
    private String method;

    @Column(length = 200)
    private String addionalNotes;
}
