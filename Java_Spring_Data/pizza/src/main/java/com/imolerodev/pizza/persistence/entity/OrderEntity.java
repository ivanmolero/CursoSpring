package com.imolerodev.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "Decimal(6, 2)")
    private Double price;

    @Column(nullable = false, columnDefinition = "Char(1)")
    private String method;

    @Column(length = 200)
    private String addionalNotes;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "idCustomer")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
