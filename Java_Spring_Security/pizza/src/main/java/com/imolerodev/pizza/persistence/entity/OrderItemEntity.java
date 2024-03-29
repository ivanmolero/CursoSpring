package com.imolerodev.pizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "idOrder", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;

    @Id
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "idPizza", insertable = false, updatable = false)
    private PizzaEntity pizza;

    @Column(columnDefinition = "Decimal(2,1)", nullable = false)
    private Double quantity;

    @Column(columnDefinition = "Decimal(4,2)", nullable = false)
    private Double price;


}
