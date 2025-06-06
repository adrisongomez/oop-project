package test.oopecommerce.models.products;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;
import com.oopecommerce.models.products.DigitalProduct;
import com.oopecommerce.models.products.Product;

public class TestDigitalProduct {

    @Test
    public void testDigitalProductEquals() {
        UUID id = UUID.randomUUID();

        DigitalProduct p1 = new DigitalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, "PDF", 2.5, "/storage1");
        DigitalProduct p2 = new DigitalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, "PDF", 2.5, "/storage1");
        DigitalProduct p3 = new DigitalProduct(UUID.randomUUID(), "Product 3", "Desc 3", Product.ProductStatus.ACTIVE, null, null, "ZIP", 10.0, "/storage2");
        DigitalProduct p4 = new DigitalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, "PDF", 2.5, "/storage2");

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4)); // Different storage
    }

    @Test
    public void testGettersAndSetters() {
        DigitalProduct product = new DigitalProduct(UUID.randomUUID(), "Product", "Desc", Product.ProductStatus.ACTIVE, null, null, "PDF", 1.0, "/storage");

        product.setFileFormat("ZIP");
        assertEquals("ZIP", product.getFileFormat());

        product.setFileSize(2.0);
        assertEquals(2.0, product.getFileSize(), 0.0);

        product.setStorage("/new-storage");
        assertEquals("/new-storage", product.getStorage());
    }

    @Test
    public void testGenerateDownloadLink() {
        UUID id = UUID.randomUUID();
        DigitalProduct product = new DigitalProduct(id, "Product", "Desc", Product.ProductStatus.ACTIVE, null, null, "PDF", 1.0, "/storage");

        String expectedLink = "/storage/" + id + "/download";
        String generatedLink = product.generateDownloadLink();

        assertEquals(expectedLink, generatedLink);
        assertEquals(expectedLink, product.getDownloadUrl());
    }

    @Test
    public void testShowDetail() {
        DigitalProduct product = new DigitalProduct(UUID.randomUUID(), "E-book", "An exciting novel", Product.ProductStatus.ACTIVE, null, null, "EPUB", 2.1, "/storage/books");

        String details = product.showDetail();
        assertTrue(details.contains("Product Name: E-book"));
        assertTrue(details.contains("File Format: EPUB"));
        assertTrue(details.contains("File Size: 2.1 MB"));
    }

    @Test
    public void testCalculateShippingCost() {
        DigitalProduct product = new DigitalProduct(UUID.randomUUID(), "E-book", "An exciting novel", Product.ProductStatus.ACTIVE, null, null, "EPUB", 2.1, "/storage/books");
        assertEquals(0.0, product.calculateShippingCost(), 0.001);
    }
} 