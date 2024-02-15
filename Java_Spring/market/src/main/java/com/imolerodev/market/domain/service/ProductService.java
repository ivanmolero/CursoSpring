package com.imolerodev.market.domain.service;

import com.imolerodev.market.domain.Product;
import com.imolerodev.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return this.productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return this.productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public boolean delete(int productId) {
        if (getProduct(productId).isPresent()) {
            this.productRepository.delete(productId);
            return true;
        }
        return false;
    }
}
