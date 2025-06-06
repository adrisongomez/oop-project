package com.oopecommerce.models.inventory;

import com.oopecommerce.models.products.DigitalProduct;
import com.oopecommerce.models.products.Product;
import java.util.HashSet;
import java.util.Set;

public class DigitalInventoryManager extends InventoryManager {
    private Set<Product> availableProducts = new HashSet<>();

    @Override
    public void addProduct(Product product, int quantity) {
        if (!(product instanceof DigitalProduct)) {
            throw new IllegalArgumentException("Only digital products can be added.");
        }
        availableProducts.add(product);
        System.out.println("Digital product " + product.getName() + " is now available for download.");
    }

    @Override
    public void removeProduct(Product product) {
        availableProducts.remove(product);
        System.out.println("Digital product " + product.getName() + " is no longer available.");
    }

    @Override
    public boolean updateStock(Product product, int quantity) {
        // Digital products have "infinite" stock, so this always succeeds if the product exists.
        return availableProducts.contains(product);
    }

    @Override
    public int getStock(Product product) {
        // Return a large number to represent infinite stock if available, otherwise 0.
        return availableProducts.contains(product) ? Integer.MAX_VALUE : 0;
    }
} 