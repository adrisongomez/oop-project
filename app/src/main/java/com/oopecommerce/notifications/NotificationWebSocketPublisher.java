package com.oopecommerce.notifications;

import jakarta.annotation.PostConstruct;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Observador que publica eventos a traves de WebSockets.
 */
@Component
public class NotificationWebSocketPublisher implements NotificationObserver {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationWebSocketPublisher(SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    @PostConstruct
    public void init() {
        EventManager manager = EventManager.getInstance();
        manager.subscribe(EventType.ORDER_STATUS_CHANGED, this);
        manager.subscribe(EventType.INVENTORY_LEVEL_CHANGED, this);
    }

    @Override
    public void update(NotificationEvent event) {
        messagingTemplate.convertAndSend("/topic/notifications", event);
    }
}
