package com.imolerodev.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class PizzaEntity extends AuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    private Boolean vegetarian;

    private Boolean vegan;

    @Column(nullable = false)
    private Boolean available;

}
