package com.oopecommerce.models.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.oopecommerce.models.orders.Order;

public class Customer extends User {

    private List<Order> purchaseHistory;
    private String preferences;

    public Customer(UUID id, String email, String hashedPassword, String name, String preferences) {
        super(id, email, hashedPassword, name);
        this.purchaseHistory = new ArrayList<>();
        this.preferences = preferences;
    }

    public List<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addOrderToHistory(Order order) {
        this.purchaseHistory.add(order);
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public boolean equals(Customer other) {
        if (other == null) return false;
        return super.equals(other) &&
               this.preferences.equals(other.getPreferences());
    }

    @Override
    public String getDashboardInfo() {
        return "Welcome, " + this.getName() + "! You have " + this.purchaseHistory.size() + " items in your purchase history.";
    }
} 