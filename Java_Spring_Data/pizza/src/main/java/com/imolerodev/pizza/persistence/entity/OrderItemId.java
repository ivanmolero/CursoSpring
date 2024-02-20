package com.imolerodev.pizza.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private OrderEntity order;

    private PizzaEntity pizza;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(
                order.getIdOrder(), that.order.getIdOrder()) &&
                Objects.equals(pizza.getIdPizza(), that.pizza.getIdPizza()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, pizza);
    }
}
