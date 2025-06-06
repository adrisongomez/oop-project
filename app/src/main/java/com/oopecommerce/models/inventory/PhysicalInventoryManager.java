package com.oopecommerce.models.inventory;

import com.oopecommerce.models.products.Product;
import java.util.HashMap;
import java.util.Map;

public class PhysicalInventoryManager extends InventoryManager {
    private Map<Product, Integer> stock = new HashMap<>();

    @Override
    public void addProduct(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        stock.put(product, getStock(product) + quantity);
    }

    @Override
    public void removeProduct(Product product) {
        stock.remove(product);
    }

    @Override
    public boolean updateStock(Product product, int quantity) {
        if (!stock.containsKey(product) || getStock(product) < quantity) {
            return false; // Not enough stock
        }
        stock.put(product, getStock(product) - quantity);
        return true;
    }

    @Override
    public int getStock(Product product) {
        return stock.getOrDefault(product, 0);
    }
} 