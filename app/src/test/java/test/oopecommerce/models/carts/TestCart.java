package test.oopecommerce.models.carts;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import com.oopecommerce.models.carts.Cart;
import com.oopecommerce.models.carts.CartLineItem;
import com.oopecommerce.models.products.ProductVariant;

public class TestCart {
    @Test
    public void cartGetTotal() {
        ProductVariant variant = new ProductVariant(UUID.randomUUID(), null, null, 0, null, 12.5, null);
        ProductVariant variant2 = new ProductVariant(UUID.randomUUID(), null, null, 0, null, 10, null);
        CartLineItem lineItem = new CartLineItem(UUID.randomUUID(), variant, 2, null, null);
        CartLineItem lineItem2 = new CartLineItem(UUID.randomUUID(), variant2, 2, null, null);

        Cart cart = new Cart(UUID.randomUUID(), Arrays.asList(lineItem, lineItem2), null, null, null);

        assertEquals(45, cart.getTotal(), 0.001);
    }

    @Test
    public void cartEquals() {
        UUID carId = UUID.randomUUID();
        Cart cart1 = new Cart(carId, Arrays.asList(), null, null, null);
        Cart cart2 = new Cart(carId, Arrays.asList(), null, null, null);
        Cart cart3 = new Cart(UUID.randomUUID(), Arrays.asList(), null, null, null);

        assertTrue(cart1.equals(cart2));
        assertFalse(cart1.equals(cart3));
    }
}
