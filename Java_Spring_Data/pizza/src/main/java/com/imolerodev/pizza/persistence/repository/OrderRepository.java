package com.imolerodev.pizza.persistence.repository;

import com.imolerodev.pizza.persistence.entity.OrderEntity;
import com.imolerodev.pizza.persistence.projections.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime fecha);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "select * from pizza_order where id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);
    
    @Query(nativeQuery = true, value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "po.total AS orderTotal, STRING_AGG(PI.name, ', ') AS pizzaNames " +
            "FROM pizza_order po " +
            "INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "INNER JOIN pizza PI ON oi.id_pizza = PI.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.id_customer, po.date, po.total")
    OrderSummary findSummary(@Param("orderId") int orderId);

}
