package com.imolerodev.pizza.web.controller;

import com.imolerodev.pizza.persistence.entity.PizzaEntity;
import com.imolerodev.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(this.pizzaService.get(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> get(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null || pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (this.pizzaService.exists(id)) {
            this.pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
