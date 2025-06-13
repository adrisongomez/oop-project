package com.oopecommerce.notifications;

public interface Observer<T> {
    void update(T event);
}
