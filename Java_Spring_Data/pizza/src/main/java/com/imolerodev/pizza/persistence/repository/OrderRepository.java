package com.imolerodev.pizza.persistence.repository;

import com.imolerodev.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime fecha);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}
