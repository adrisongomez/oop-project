package com.oopecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.products.Product;

public interface IProductRepository {
    Optional<Product> findById(UUID id);
    void save(Product product);
    void delete(UUID id);
    Iterable<Product> findAll();
    Iterable<Product> search(String query, int limit, int offset);
}
