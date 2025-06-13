package com.oopecommerce.models.orders;

import com.oopecommerce.notifications.Observable;

public class OrderStatusNotifier extends Observable<OrderStatusChangeEvent> {
    private static final OrderStatusNotifier INSTANCE = new OrderStatusNotifier();

    private OrderStatusNotifier() {
    }

    public static OrderStatusNotifier getInstance() {
        return INSTANCE;
    }

    public void notifyStatusChange(Order order, Order.OrderStatus oldStatus, Order.OrderStatus newStatus) {
        notifyObservers(new OrderStatusChangeEvent(order, oldStatus, newStatus));
    }
}
