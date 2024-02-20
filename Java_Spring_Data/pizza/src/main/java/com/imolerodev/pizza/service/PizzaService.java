package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PizzaEntity> getAll() {
        String sql = "SELECT * FROM pizza";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}
