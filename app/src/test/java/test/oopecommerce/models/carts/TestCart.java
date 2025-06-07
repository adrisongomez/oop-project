package test.oopecommerce.models.carts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.oopecommerce.models.carts.Cart;
import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.PhysicalProduct;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.ProductVariant;
import com.oopecommerce.models.users.User;

public class TestCart {
    @Test
    public void testCartTotal() {
        User user = new User(UUID.randomUUID(), "test@user.com", "password", "Test User");
        Cart cart = new Cart(user);

        ProductVariant variant1 = new ProductVariant(UUID.randomUUID(), "SKU001", null, 0, null, 12.5, null);
        ProductVariant variant2 = new ProductVariant(UUID.randomUUID(), "SKU002", null, 0, null, 10, null);
        
        cart.addItem(variant1, 2); // 2 * 12.5 = 25.0
        cart.addItem(variant2, 2); // 2 * 10.0 = 20.0

        assertEquals(45.0, cart.getTotal(), 0.001);
    }

    @Test
    public void testCartEquals() {
        User user = new User(UUID.randomUUID(), "test@user.com", "password", "Test User");
        Cart cart1 = new Cart(user);
        Cart cart2 = cart1; // For equality check, it must be the same object ID
        Cart cart3 = new Cart(user);

        assertTrue(cart1.equals(cart2));
        assertFalse(cart1.equals(cart3));
    }

    @Test
    public void testCartEqualsNull() {
        User user = new User(UUID.randomUUID(), "test@user.com", "password", "Test User");
        Cart cart = new Cart(user);

        assertFalse(cart.equals(null));
    }

    @Test
    public void testAddItemOverloading() {
        User user = new User(UUID.randomUUID(), "test@user.com", "password", "Test User");
        Cart cart = new Cart(user);

        ProductVariant variant1 = new ProductVariant(UUID.randomUUID(), "SKU001", Arrays.asList("Red", "Large"), 0, null, 29.99, "200");
        ProductVariant variant2 = new ProductVariant(UUID.randomUUID(), "SKU002", Arrays.asList("Blue", "Medium"), 1, null, 25.99, "180");
        List<ProductVariant> variants = new ArrayList<>(Arrays.asList(variant1, variant2));

        InventoryLocation location = new InventoryLocation("A1", "Warehouse", "City", "Country");
        Product product = new PhysicalProduct(UUID.randomUUID(), "T-Shirt", "A cool t-shirt", Product.ProductStatus.ACTIVE, null, variants, 0.2, "M", location);

        // Test: addItem(ProductVariant variant, int quantity)
        cart.addItem(variant1, 2);
        assertEquals(1, cart.getLineItems().size());
        assertEquals(2, cart.getLineItems().get(0).getQuantity());
        assertEquals(29.99, cart.getLineItems().get(0).getVariant().getPrice(), 0.0);

        // Test: addItem(Product product, String sku, int quantity)
        cart.addItem(product, "SKU002", 3);
        assertEquals(2, cart.getLineItems().size());
        assertEquals(3, cart.getLineItems().get(1).getQuantity());

        // Test adding same variant again to update quantity
        cart.addItem(variant1, 1);
        assertEquals(2, cart.getLineItems().size());
        assertEquals(3, cart.getLineItems().get(0).getQuantity());

        // Test: addItem(Product product, int quantity) - adds first variant
        Cart cart2 = new Cart(user);
        Product product2 = new PhysicalProduct(UUID.randomUUID(), "Mug", "A nice mug", Product.ProductStatus.ACTIVE, null, new ArrayList<>(Arrays.asList(variant2, variant1)), 0.5, "Standard", location);
        cart2.addItem(product2, 5);
        assertEquals(1, cart2.getLineItems().size());
        assertEquals("SKU001", cart2.getLineItems().get(0).getVariant().getSku());
        assertEquals(5, cart2.getLineItems().get(0).getQuantity());
    }

    @Test
    public void testRemoveItem() {
        User user = new User(UUID.randomUUID(), "test@user.com", "password", "Test User");
        Cart cart = new Cart(user);
        ProductVariant variant1 = new ProductVariant(UUID.randomUUID(), "SKU001", null, 0, null, 10.0, "100");
        ProductVariant variant2 = new ProductVariant(UUID.randomUUID(), "SKU002", null, 1, null, 20.0, "200");
        cart.addItem(variant1, 5);
        cart.addItem(variant2, 3);

        // Test: removeItem(variant, quantity) - reduce quantity
        cart.removeItem(variant1, 2);
        assertEquals(2, cart.getLineItems().size());
        assertEquals(3, cart.getLineItems().get(0).getQuantity());

        // Test: removeItem(variant, quantity) - remove item if quantity is 0 or less
        cart.removeItem(variant1, 3);
        assertEquals(1, cart.getLineItems().size());
        assertEquals("SKU002", cart.getLineItems().get(0).getVariant().getSku());

        // Test: removeItem(variant) - remove item completely
        cart.addItem(variant1, 2);
        assertEquals(2, cart.getLineItems().size());
        cart.removeItem(variant2);
        assertEquals(1, cart.getLineItems().size());
        assertEquals("SKU001", cart.getLineItems().get(0).getVariant().getSku());
    }
}
