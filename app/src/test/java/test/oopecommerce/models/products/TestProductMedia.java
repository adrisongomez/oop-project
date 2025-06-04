package test.oopecommerce.models.products;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.oopecommerce.models.products.ProductMedia;

public class TestProductMedia {
    @Test
    public void productMediaEquals() {
        UUID pmId1 = UUID.randomUUID();
        ProductMedia pm1 = new ProductMedia(pmId1, null, null, null, 0);
        ProductMedia pm2 = new ProductMedia(pmId1, null, null, null, 1);
        ProductMedia pm3 = new ProductMedia(UUID.randomUUID(), null, null, null, 2);

        assertTrue(pm1.equals(pm2));
        assertFalse(pm3.equals(pm2));
    }
}
