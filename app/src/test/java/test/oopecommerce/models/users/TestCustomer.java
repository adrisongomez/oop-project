package test.oopecommerce.models.users;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;
import com.oopecommerce.models.users.Customer;
import com.oopecommerce.models.orders.Order;

public class TestCustomer {

    @Test
    public void testCustomerEquals() {
        UUID id = UUID.randomUUID();
        Customer c1 = new Customer(id, "test@test.com", "pass", "Test User", "pref1");
        Customer c2 = new Customer(id, "test@test.com", "pass", "Test User", "pref1");
        Customer c3 = new Customer(UUID.randomUUID(), "test3@test.com", "pass", "Test User 3", "pref3");
        Customer c4 = new Customer(id, "test@test.com", "pass", "Test User", "pref2");

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(c4)); // Different preferences
    }

    @Test
    public void testPreferences() {
        Customer customer = new Customer(UUID.randomUUID(), "test@test.com", "pass", "Test", "none");
        customer.setPreferences("new-prefs");
        assertEquals("new-prefs", customer.getPreferences());
    }

    @Test
    public void testPurchaseHistory() {
        Customer customer = new Customer(UUID.randomUUID(), "test@test.com", "pass", "Test", "none");
        assertTrue(customer.getPurchaseHistory().isEmpty());

        // The Order class needs to be accessible for this test
        // Assuming a simple Order class exists in com.oopecommerce.models.orders
        Order order1 = new Order();
        Order order2 = new Order();

        customer.addOrderToHistory(order1);
        assertEquals(1, customer.getPurchaseHistory().size());
        
        customer.addOrderToHistory(order2);
        assertEquals(2, customer.getPurchaseHistory().size());
    }
} 