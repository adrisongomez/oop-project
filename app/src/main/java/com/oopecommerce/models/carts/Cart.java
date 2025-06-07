package com.oopecommerce.models.carts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.users.User;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.ProductVariant;

public class Cart {
    final UUID id;
    List<CartLineItem> lineItems;
    final User user;
    final Date createdAt;
    Date updatedAt;

    public Cart(User user) {
        this.id = UUID.randomUUID();
        this.lineItems = new ArrayList<>();
        this.user = user;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Double getTotal() {
        Optional<Double> value = this.lineItems.stream()
                .map(CartLineItem::getTotal)
                .reduce((acc, item) -> acc + item);
        return value.orElse(0.0);
    }

    public void addItem(ProductVariant variant, int quantity) {
        Optional<CartLineItem> existingItem = lineItems.stream()
            .filter(item -> item.getVariant().equals(variant))
            .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            lineItems.add(new CartLineItem(UUID.randomUUID(), variant, quantity, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        }
        this.updatedAt = new Date(System.currentTimeMillis());
    }
    
    // Overloaded method
    public void addItem(Product product, String sku, int quantity) {
        Optional<ProductVariant> variant = product.getVariants().stream()
            .filter(v -> v.getSku().equals(sku))
            .findFirst();

        if (variant.isPresent()) {
            addItem(variant.get(), quantity);
        } else {
            // Handle case where SKU is not found for the product
            System.out.println("Variant with SKU " + sku + " not found for product " + product.getName());
        }
    }

    // Overloaded method
    public void addItem(Product product, int quantity) {
        // Adds the first available variant of the product
        product.getVariants().stream().findFirst().ifPresent(v -> addItem(v, quantity));
    }

    public void removeItem(ProductVariant variant) {
        lineItems.removeIf(item -> item.getVariant().equals(variant));
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    // Overloaded method
    public void removeItem(ProductVariant variant, int quantity) {
        Optional<CartLineItem> existingItem = lineItems.stream()
            .filter(item -> item.getVariant().equals(variant))
            .findFirst();

        if (existingItem.isPresent()) {
            CartLineItem item = existingItem.get();
            int newQuantity = item.getQuantity() - quantity;
            if (newQuantity > 0) {
                item.setQuantity(newQuantity);
            } else {
                lineItems.remove(item);
            }
            this.updatedAt = new Date(System.currentTimeMillis());
        }
    }

    public Boolean equals(Cart cart) {
        return cart != null && this.id.equals(cart.id);
    }

    public UUID getId() {
        return id;
    }

    public List<CartLineItem> getLineItems() {
        return lineItems;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
