package com.oopecommerce.models.orders;

import java.util.UUID;

public class Order {
    private final UUID id;

    public Order() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
} 