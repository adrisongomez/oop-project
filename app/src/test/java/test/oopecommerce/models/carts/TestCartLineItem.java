package test.oopecommerce.models.carts;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.oopecommerce.models.carts.CartLineItem;
import com.oopecommerce.models.products.ProductVariant;

public class TestCartLineItem {
    @Test
    public void cartLineItemEquals() {
        ProductVariant variant = new ProductVariant(UUID.randomUUID(), null, null, 0, null, 0, null);
        ProductVariant variant2 = new ProductVariant(UUID.randomUUID(), null, null, 0, null, 0, null);
        CartLineItem lineItem1 = new CartLineItem(null, variant, 1, null, null);
        CartLineItem lineItem2 = new CartLineItem(null, variant, 1, null, null);
        CartLineItem lineItem3 = new CartLineItem(null, variant2, 1, null, null);

        assertTrue(lineItem1.equals(lineItem2));
        assertFalse(lineItem1.equals(lineItem3));
    }

    @Test
    public void cartLineItemTotal() {
        ProductVariant variant = new ProductVariant(UUID.randomUUID(), null, null, 0, null, 100.0, null);
        CartLineItem lineItem1 = new CartLineItem(null, variant, 10, null, null);

        assertEquals(1000.0, lineItem1.getTotal(), 0.0001);
    }
}
