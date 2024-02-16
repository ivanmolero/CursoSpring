package com.imolerodev.market.persistence;

import com.imolerodev.market.domain.Purchase;
import com.imolerodev.market.domain.repository.PurchaseRepository;
import com.imolerodev.market.persistence.crud.CompraCrudRepository;
import com.imolerodev.market.persistence.entity.Compra;
import com.imolerodev.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) this.compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByCliente(String clientId) {
        return this.compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> this.purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = this.purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
