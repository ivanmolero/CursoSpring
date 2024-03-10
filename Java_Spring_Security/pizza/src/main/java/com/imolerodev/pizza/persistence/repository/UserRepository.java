package com.imolerodev.pizza.persistence.repository;

import com.imolerodev.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
