package test.oopecommerce.models.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import com.oopecommerce.models.addresses.ShippingAddress;
import com.oopecommerce.models.orders.Order;
import com.oopecommerce.models.orders.OrderStatusChangeEvent;
import com.oopecommerce.models.orders.OrderStatusNotifier;
import com.oopecommerce.notifications.Observer;

public class TestOrderObserver {
    @Test
    public void observersAreNotifiedOnStatusChange() {
        ShippingAddress address = new ShippingAddress("Street", "City", "State", "0000", "Country");
        Order order = new Order(address);

        AtomicReference<OrderStatusChangeEvent> received = new AtomicReference<>();
        Observer<OrderStatusChangeEvent> observer = received::set;

        OrderStatusNotifier notifier = OrderStatusNotifier.getInstance();
        notifier.subscribe(observer);

        order.setStatus(Order.OrderStatus.SHIPPED);

        assertNotNull(received.get());
        assertEquals(Order.OrderStatus.PENDING, received.get().getOldStatus());
        assertEquals(Order.OrderStatus.SHIPPED, received.get().getNewStatus());

        notifier.unsubscribe(observer);
    }
}
