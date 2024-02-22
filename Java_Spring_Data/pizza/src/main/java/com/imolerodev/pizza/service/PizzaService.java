package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.PizzaEntity;
import com.imolerodev.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAllAvailable() {
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getAllWithIngredient(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getAllWithoutIngredient(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean exists(int id) {
        return pizzaRepository.existsById(id);
    }

    public void delete(int id) {
        pizzaRepository.deleteById(id);
    }
}
