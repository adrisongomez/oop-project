package test.oopecommerce.models.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.oopecommerce.models.products.ProductVariant;

public class TestProductVariant {
        @Test
        public void productVariantEquals() {
                UUID pv1Id = UUID.randomUUID();
                ProductVariant pv1 = new ProductVariant(
                                pv1Id,
                                null,
                                null,
                                0,
                                null,
                                0,
                                "");

                ProductVariant pv2 = new ProductVariant(
                                UUID.randomUUID(),
                                "123",
                                null,
                                0,
                                null,
                                0,
                                "");

                ProductVariant pv3 = new ProductVariant(
                                pv1Id,
                                "123",
                                null,
                                0,
                                null,
                                0,
                                "");

                assertFalse(pv1.equals(pv2));
                assertFalse(pv2.equals(pv3));
                assertTrue(pv1.equals(pv3));
        }

        @Test
        public void productVariantEqualsNull() {
                ProductVariant pv = new ProductVariant(
                                UUID.randomUUID(),
                                "sku",
                                null,
                                0,
                                null,
                                0,
                                "");

                assertFalse(pv.equals(null));
        }
}
