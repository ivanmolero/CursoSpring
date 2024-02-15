package com.imolerodev.market.persistence;

import com.imolerodev.market.domain.Product;
import com.imolerodev.market.domain.repository.ProductRepository;
import com.imolerodev.market.persistence.crud.ProductoCrudRepository;
import com.imolerodev.market.persistence.entity.Producto;
import com.imolerodev.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// la anotación @Repository indica que esta clase interactua con la base de datos
// tambien puede usarse @Component, solo que esta anotación es muy genérica
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productos1 -> mapper.toProducts(productos1));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(producto1 -> mapper.toProduct(producto1));
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(productoCrudRepository.save(mapper.toProducto(product)));
    }

    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}
