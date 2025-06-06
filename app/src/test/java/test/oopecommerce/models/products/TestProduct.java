package test.oopecommerce.models.products;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.PhysicalProduct;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.ProductMedia;
import com.oopecommerce.models.products.ProductVariant;

public class TestProduct {
        @Test
        public void productEquals() {
                InventoryLocation location = new InventoryLocation("code", "street", "city", "country");
                Product p1 = null;
                UUID p2Id = UUID.randomUUID();
                Product p2 = new PhysicalProduct(p2Id, "product 2", "Lorem ipsum", Product.ProductStatus.ACTIVE, null, null, 1.0, "dims", location);
                Product p3 = new PhysicalProduct(UUID.randomUUID(), "product 3", "Lorem ipsum", Product.ProductStatus.ACTIVE, null, null, 1.0, "dims", location);
                Product p4 = new PhysicalProduct(p2Id, "product 3", "Lorem ipsum", Product.ProductStatus.ACTIVE, null, null, 1.0, "dims", location);

                assertFalse(p2.equals(p1));
                assertFalse(p2.equals(p3));
                assertTrue(p2.equals(p4));
        }

        @Test
        public void productSortImages() {
                ArrayList<ProductMedia> images = new ArrayList<ProductMedia>();
                InventoryLocation location = new InventoryLocation("code", "street", "city", "country");

                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 0));
                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 2));
                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 3));
                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 5));
                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 4));
                images.add(new ProductMedia(UUID.randomUUID(), "Alt Test", "www.wey.com", "image/jpg", 1));
                Product product = new PhysicalProduct(
                                UUID.randomUUID(),
                                "Product-1",
                                "Lorem Ipsum",
                                Product.ProductStatus.ACTIVE,
                                images, null, 1.0, "dims", location);

                ArrayList<Integer> expectedPositions = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
                ArrayList<Integer> actualPositions = new ArrayList<>();
                for (ProductMedia img : product.getMedias()) {
                        actualPositions.add(img.getPosition());
                }
                assertEquals(expectedPositions, actualPositions);
        }

        @Test
        public void productSortVariants() {
                ArrayList<ProductVariant> variants = new ArrayList<ProductVariant>();
                InventoryLocation location = new InventoryLocation("code", "street", "city", "country");

                variants.add(new ProductVariant(UUID.randomUUID(), "SKU 1", Arrays.asList("XS"), 0, null, 0, "10"));
                variants.add(new ProductVariant(UUID.randomUUID(), "SKU 2", Arrays.asList("LG"), 3, null, 0, "10"));
                variants.add(new ProductVariant(UUID.randomUUID(), "SKU 3", Arrays.asList("MD"), 2, null, 0, "10"));
                variants.add(new ProductVariant(UUID.randomUUID(), "SKU 4", Arrays.asList("XL"), 1, null, 0, "10"));
                Product product = new PhysicalProduct(UUID.randomUUID(), "Product-1", "Lorem Ipsum", Product.ProductStatus.ACTIVE, null, variants, 1.0, "dims", location);
                int[] expectedValue = { 0, 1, 2, 3 };
                int[] value = product.getVariants().stream().mapToInt(variant -> variant.getPosition()).toArray();
                assertArrayEquals(expectedValue, value);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testProductVariantNegativePrice() {
                new ProductVariant(UUID.randomUUID(), "SKU-NEG", null, 0, null, -10.0, "100");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testProductVariantNegativePosition() {
                new ProductVariant(UUID.randomUUID(), "SKU-NEG", null, -1, null, 10.0, "100");
        }
}
