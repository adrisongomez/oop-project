package com.oopecommerce.models.users;

import java.util.UUID;

import com.oopecommerce.models.products.Product;

public class Admin extends User {

    public Admin(UUID id, String email, String hashedPassword, String name) {
        super(id, email, hashedPassword, name);
    }

    public void manageInventory(Product product, int quantity) {
        // Logic to manage inventory
        System.out.println("Managing inventory for product: " + product.getName() + ", new quantity: " + quantity);
    }

    public void setPromotion(Product product, double discount) {
        // Logic to set a promotion
        System.out.println("Setting promotion for product: " + product.getName() + ", discount: " + discount);
    }

    public boolean equals(Admin other) {
        if (other == null) return false;
        return super.equals(other);
    }

    @Override
    public String getDashboardInfo() {
        return "Admin Dashboard for " + this.getName() + ". Manage products, users, and promotions.";
    }
} 