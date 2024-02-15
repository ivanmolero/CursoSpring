package com.imolerodev.market.domain.repository;

import com.imolerodev.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByCliente(String clientId);
    Purchase save(Purchase purchase);
}
