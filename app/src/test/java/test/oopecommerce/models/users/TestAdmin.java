package test.oopecommerce.models.users;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;
import com.oopecommerce.models.users.Admin;
import com.oopecommerce.models.products.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestAdmin {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @org.junit.Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAdminEquals() {
        UUID id = UUID.randomUUID();
        Admin a1 = new Admin(id, "admin1@test.com", "pass", "Admin User 1");
        Admin a2 = new Admin(id, "admin1@test.com", "pass", "Admin User 1");
        Admin a3 = new Admin(UUID.randomUUID(), "admin3@test.com", "pass", "Admin User 3");

        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(a3));
    }

    @Test
    public void testManageInventory() {
        Admin admin = new Admin(UUID.randomUUID(), "admin@test.com", "pass", "Admin");
        Product product = new Product(UUID.randomUUID(), "Test Product", "Desc", Product.ProductStatus.ACTIVE, null, null);
        admin.manageInventory(product, 100);
        assertEquals("Managing inventory for product: Test Product, new quantity: 100\n", outContent.toString());
    }

    @Test
    public void testSetPromotion() {
        Admin admin = new Admin(UUID.randomUUID(), "admin@test.com", "pass", "Admin");
        Product product = new Product(UUID.randomUUID(), "Test Product", "Desc", Product.ProductStatus.ACTIVE, null, null);
        admin.setPromotion(product, 0.2);
        assertEquals("Setting promotion for product: Test Product, discount: 0.2\n", outContent.toString());
    }

    @Test
    public void testGetDashboardInfo() {
        Admin admin = new Admin(UUID.randomUUID(), "admin@test.com", "pass", "Jane Admin");
        String info = admin.getDashboardInfo();
        assertTrue(info.contains("Admin Dashboard for Jane Admin"));
    }
} 