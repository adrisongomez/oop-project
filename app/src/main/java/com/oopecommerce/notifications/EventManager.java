package com.oopecommerce.notifications;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static final EventManager instance = new EventManager();
    private final Map<EventType, List<NotificationObserver>> observers = new EnumMap<>(EventType.class);

    private EventManager() {}

    public static EventManager getInstance() {
        return instance;
    }

    public void subscribe(EventType type, NotificationObserver observer) {
        observers.computeIfAbsent(type, k -> new ArrayList<>()).add(observer);
    }

    public void unsubscribe(EventType type, NotificationObserver observer) {
        List<NotificationObserver> list = observers.get(type);
        if (list != null) {
            list.remove(observer);
        }
    }

    public void notify(EventType type, NotificationEvent event) {
        List<NotificationObserver> list = observers.get(type);
        if (list != null) {
            // clone the list to avoid ConcurrentModificationException
            for (NotificationObserver ob : new ArrayList<>(list)) {
                ob.update(event);
            }
        }
    }
}
