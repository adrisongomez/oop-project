package test.oopecommerce.models.users;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.oopecommerce.models.users.Customer;
import com.oopecommerce.models.orders.Order;
import com.oopecommerce.models.addresses.ShippingAddress;

public class TestCustomer {

    private final ShippingAddress genericAddress = new ShippingAddress("123 Main", "Anytown", "Anystate", "12345", "USA");

    @Test
    public void testCustomerEquals() {
        UUID id = UUID.randomUUID();
        Customer c1 = new Customer(id, "test@test.com", "pass", "Test User", "pref1");
        Customer c2 = new Customer(id, "test@test.com", "pass", "Test User", "pref1");
        Customer c3 = new Customer(UUID.randomUUID(), "test3@test.com", "pass", "Test User 3", "pref3");
        Customer c4 = new Customer(id, "test@test.com", "pass", "Test User", "pref2");

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(c4));
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
        Order order1 = new Order(new Date(), Order.OrderStatus.PENDING, genericAddress, 0);
        Order order2 = new Order(new Date(), Order.OrderStatus.PENDING, genericAddress, 0);
        customer.addOrderToHistory(order1);
        assertEquals(1, customer.getPurchaseHistory().size());
        customer.addOrderToHistory(order2);
        assertEquals(2, customer.getPurchaseHistory().size());
    }

    @Test
    public void testGetDashboardInfo() {
        Customer customer = new Customer(UUID.randomUUID(), "test@test.com", "pass", "John Doe", "none");
        customer.addOrderToHistory(new Order(new Date(), Order.OrderStatus.PENDING, genericAddress, 0));
        String info = customer.getDashboardInfo();
        assertTrue(info.contains("John Doe"));
        assertTrue(info.contains("1 items in your purchase history"));
    }

    @Test
    public void testViewOrderHistoryOverloading() {
        Customer customer = new Customer(UUID.randomUUID(), "test@test.com", "pass", "Test User", "none");
        
        Order order1 = new Order(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(10)), Order.OrderStatus.DELIVERED, genericAddress, 10.0);
        Order order2 = new Order(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(5)), Order.OrderStatus.SHIPPED, genericAddress, 5.0);
        Order order3 = new Order(new Date(), Order.OrderStatus.PENDING, genericAddress, 15.0);
        
        customer.addOrderToHistory(order1);
        customer.addOrderToHistory(order2);
        customer.addOrderToHistory(order3);

        assertEquals(3, customer.viewOrderHistory().size());
        Date filterDate = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7));
        assertEquals(2, customer.viewOrderHistory(filterDate).size());
        assertEquals(1, customer.viewOrderHistory(Order.OrderStatus.DELIVERED).size());
        assertEquals(1, customer.viewOrderHistory(Order.OrderStatus.PENDING).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserInvalidEmail() {
        new Customer(UUID.randomUUID(), "invalid-email", "pass", "Test User", "none");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserEmptyName() {
        new Customer(UUID.randomUUID(), "valid@email.com", "pass", "  ", "none");
    }
} 