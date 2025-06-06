package com.oopecommerce.models.inventory;

import com.oopecommerce.models.products.Product;

public abstract class InventoryManager {

    public abstract void addProduct(Product product, int quantity);

    public abstract void removeProduct(Product product);

    public abstract boolean updateStock(Product product, int quantity);

    public abstract int getStock(Product product);
} 