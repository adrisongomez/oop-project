package test.oopecommerce.models.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.oopecommerce.models.addresses.ShippingAddress;
import com.oopecommerce.models.orders.Order;
import com.oopecommerce.models.orders.Order.OrderStatus;
import com.oopecommerce.notifications.*;

class TestObserver implements NotificationObserver {
    List<NotificationEvent> events = new ArrayList<>();

    @Override
    public void update(NotificationEvent event) {
        events.add(event);
    }
}

public class TestOrderNotifications {

    @Test
    public void observerReceivesStatusChangeEvent() {
        ShippingAddress address = new ShippingAddress("street", "city", "state", "zip", "country");
        Order order = new Order(address);

        TestObserver observer = new TestObserver();
        EventManager manager = EventManager.getInstance();
        manager.subscribe(EventType.ORDER_STATUS_CHANGED, observer);

        order.setStatus(OrderStatus.SHIPPED);

        assertEquals(1, observer.events.size());
        NotificationEvent evt = observer.events.get(0);
        assertTrue(evt instanceof OrderStatusChangeEvent);
        OrderStatusChangeEvent event = (OrderStatusChangeEvent) evt;
        assertEquals(order, event.getOrder());
        assertEquals(OrderStatus.PENDING, event.getOldStatus());
        assertEquals(OrderStatus.SHIPPED, event.getNewStatus());

        manager.unsubscribe(EventType.ORDER_STATUS_CHANGED, observer);
    }
}
