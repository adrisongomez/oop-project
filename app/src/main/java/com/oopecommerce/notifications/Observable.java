package com.oopecommerce.notifications;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    private final List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer<T> observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(T event) {
        for (Observer<T> observer : observers) {
            observer.update(event);
        }
    }
}
