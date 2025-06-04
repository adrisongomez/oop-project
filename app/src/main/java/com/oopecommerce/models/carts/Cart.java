package com.oopecommerce.models.carts;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.users.User;

public class Cart {
    final UUID id;
    List<CartLineItem> lineItems;
    final User user;
    final Date createdAt;
    Date updatedAt;

    public Cart(UUID id, List<CartLineItem> lineItems, User user, Date createdAt, Date updatedAt) {
        this.id = id;
        this.lineItems = lineItems;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Double getTotal() {
        Optional<Double> value = this.lineItems.stream()
                .map((item) -> item.quantity * item.variant.getPrice())
                .reduce((acc, item) -> acc + item);
        return value.isPresent() ? value.get() : 0;
    }

    public Boolean equals(Cart cart) {
        return this.id.equals(cart.id);
    }

    public UUID getId() {
        return id;
    }

    public List<CartLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<CartLineItem> lineItems) {
        this.lineItems = lineItems;
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
