package com.imolerodev.market.persistence;

import com.imolerodev.market.persistence.crud.ProductoCrudRepository;
import com.imolerodev.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
