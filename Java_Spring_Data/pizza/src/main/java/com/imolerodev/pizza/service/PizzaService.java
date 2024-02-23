package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.PizzaEntity;
import com.imolerodev.pizza.persistence.repository.PizzaPagSortRepository;
import com.imolerodev.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaPagSortRepository pizzaPagSortRepository;

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public List<PizzaEntity> getAllAvailable() {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("la pizza no existe"));
    }

    public List<PizzaEntity> getAllWithIngredient(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getAllWithoutIngredient(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
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
