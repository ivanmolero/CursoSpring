package com.imolerodev.pizza.persistence.repository;

import com.imolerodev.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
    @Query(value = "select c from CustomerEntity c where c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
