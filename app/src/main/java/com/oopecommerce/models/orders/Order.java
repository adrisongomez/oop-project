package com.oopecommerce.models.orders;

import java.util.Date;
import java.util.UUID;
import com.oopecommerce.models.addresses.ShippingAddress;
import com.oopecommerce.notifications.EventManager;
import com.oopecommerce.notifications.EventType;
import com.oopecommerce.notifications.OrderStatusChangeEvent;

public class Order {
    public enum OrderStatus {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    private final UUID id;
    private final Date orderDate;
    private OrderStatus status;
    private ShippingAddress shippingAddress;
    private double taxes;

    public Order(ShippingAddress shippingAddress) {
        this.id = UUID.randomUUID();
        this.orderDate = new Date(); // Sets to current date
        this.status = OrderStatus.PENDING;
        this.shippingAddress = shippingAddress;
        this.taxes = 0.0; // Default taxes
    }
    
    // Overloaded constructor for testing
    public Order(Date orderDate, OrderStatus status, ShippingAddress shippingAddress, double taxes) {
        this.id = UUID.randomUUID();
        this.orderDate = orderDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.taxes = taxes;
    }

    public UUID getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (this.status != status) {
            OrderStatus previous = this.status;
            this.status = status;
            EventManager.getInstance().notify(
                EventType.ORDER_STATUS_CHANGED,
                new OrderStatusChangeEvent(this, previous, status));
        }
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }
} 