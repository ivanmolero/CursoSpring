package com.imolerodev.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "idOrder")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "idPizza")
    private PizzaEntity pizza;

    @Column(columnDefinition = "Decimal(2,1)", nullable = false)
    private Double quantity;

    @Column(columnDefinition = "Decimal(3,2)", nullable = false)
    private Double price;
}
