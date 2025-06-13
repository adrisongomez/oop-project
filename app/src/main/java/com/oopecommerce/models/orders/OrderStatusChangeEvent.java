package com.oopecommerce.models.orders;

public class OrderStatusChangeEvent {
    private final Order order;
    private final Order.OrderStatus oldStatus;
    private final Order.OrderStatus newStatus;

    public OrderStatusChangeEvent(Order order, Order.OrderStatus oldStatus, Order.OrderStatus newStatus) {
        this.order = order;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public Order getOrder() {
        return order;
    }

    public Order.OrderStatus getOldStatus() {
        return oldStatus;
    }

    public Order.OrderStatus getNewStatus() {
        return newStatus;
    }
}
