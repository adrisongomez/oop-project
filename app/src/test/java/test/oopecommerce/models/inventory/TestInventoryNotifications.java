package test.oopecommerce.models.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.oopecommerce.models.inventory.InventoryLevel;
import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.SimpleProduct;
import com.oopecommerce.notifications.*;

public class TestInventoryNotifications {

    static class TestObserver implements NotificationObserver {
        List<NotificationEvent> events = new ArrayList<>();
        @Override
        public void update(NotificationEvent event) {
            events.add(event);
        }
    }

    @Test
    public void inventoryChangeFiresEvent() {
        InventoryLocation loc = new InventoryLocation("W1", "Street", "City", "Country");
        SimpleProduct prod = new SimpleProduct(java.util.UUID.randomUUID(), "prod", "desc", com.oopecommerce.models.products.Product.ProductStatus.ACTIVE);
        InventoryLevel level = new InventoryLevel(prod, loc, 5);

        TestObserver ob = new TestObserver();
        EventManager mgr = EventManager.getInstance();
        mgr.subscribe(EventType.INVENTORY_LEVEL_CHANGED, ob);

        level.setQuantity(10);

        assertEquals(1, ob.events.size());
        assertTrue(ob.events.get(0) instanceof InventoryLevelChangeEvent);
        mgr.unsubscribe(EventType.INVENTORY_LEVEL_CHANGED, ob);
    }
}
