package com.imolerodev.pizza.web.controller;

import com.imolerodev.pizza.persistence.entity.PizzaEntity;
import com.imolerodev.pizza.service.PizzaService;
import com.imolerodev.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "4") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "4") int elements,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        return ResponseEntity.ok(this.pizzaService.getAllAvailable(page, elements, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(this.pizzaService.get(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getAllWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getAllWithIngredient(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getAllWithout(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getAllWithoutIngredient(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable Double price) {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto pizzaPriceDto) {
        if (this.pizzaService.exists(pizzaPriceDto.getPizzaId())) {
            this.pizzaService.updatePrice(pizzaPriceDto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
