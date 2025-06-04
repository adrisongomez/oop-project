package com.oopecommerce.models.carts;

import java.util.Date;
import java.util.UUID;

import com.oopecommerce.models.products.ProductVariant;

public class CartLineItem {
    final UUID id;
    ProductVariant variant;
    int quantity;
    Date createdAt;
    Date updatedAt;

    public CartLineItem(UUID id, ProductVariant variant, int quantity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.variant = variant;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public double getTotal() {
        return this.quantity * this.variant.getPrice();
    }

    public Boolean equals(CartLineItem cart) {
        return cart.variant.equals(this.variant);
    }

    public UUID getId() {
        return id;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
