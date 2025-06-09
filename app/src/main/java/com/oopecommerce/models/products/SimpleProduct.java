package com.oopecommerce.models.products;

import java.util.UUID;

import jakarta.persistence.Entity;

/**
 * Minimal concrete implementation of {@link Product} used for basic CRUD operations.
 */
@Entity
public class SimpleProduct extends Product {
    protected SimpleProduct() {
        // Hibernate
    }
    public SimpleProduct(UUID id, String name, String description, ProductStatus status) {
        super(id, name, description, status, null, null);
    }

    @Override
    public double calculateShippingCost() {
        return 0.0;
    }
}
