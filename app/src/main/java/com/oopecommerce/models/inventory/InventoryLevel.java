package com.oopecommerce.models.inventory;

import java.util.UUID;

import com.oopecommerce.models.products.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_levels")
public class InventoryLevel {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private InventoryLocation location;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    protected InventoryLevel() {
        // Required by Hibernate
    }

    public InventoryLevel(UUID id, Product product, InventoryLocation location, int quantity) {
        this.id = id;
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }

    public InventoryLevel(Product product, InventoryLocation location, int quantity) {
        this(UUID.randomUUID(), product, location, quantity);
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InventoryLocation getLocation() {
        return location;
    }

    public void setLocation(InventoryLocation location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
