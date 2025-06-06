package test.oopecommerce.models.products;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;
import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.PhysicalProduct;
import com.oopecommerce.models.products.Product;

public class TestPhysicalProduct {

    @Test
    public void testPhysicalProductEquals() {
        UUID id = UUID.randomUUID();
        InventoryLocation location1 = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        InventoryLocation location2 = new InventoryLocation("WH2", "456 Oak Ave", "CityB", "CountryB");

        PhysicalProduct p1 = new PhysicalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, 10.5, "10x10x10", location1);
        PhysicalProduct p2 = new PhysicalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, 10.5, "10x10x10", location1);
        PhysicalProduct p3 = new PhysicalProduct(UUID.randomUUID(), "Product 3", "Desc 3", Product.ProductStatus.ACTIVE, null, null, 15.0, "20x20x20", location2);
        PhysicalProduct p4 = new PhysicalProduct(id, "Product 1", "Desc 1", Product.ProductStatus.ACTIVE, null, null, 10.5, "10x10x10", location2);

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4)); // Different location
    }

    @Test
    public void testGettersAndSetters() {
        InventoryLocation location = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        PhysicalProduct product = new PhysicalProduct(UUID.randomUUID(), "Product", "Desc", Product.ProductStatus.ACTIVE, null, null, 1.0, "1x1x1", location);

        product.setWeight(2.0);
        assertEquals(2.0, product.getWeight(), 0.0);

        product.setDimensions("2x2x2");
        assertEquals("2x2x2", product.getDimensions());

        InventoryLocation newLocation = new InventoryLocation("WH2", "456 Oak Ave", "CityB", "CountryB");
        product.setInventoryLocation(newLocation);
        assertEquals(newLocation, product.getInventoryLocation());
    }

    @Test
    public void testShowDetail() {
        InventoryLocation location = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        PhysicalProduct product = new PhysicalProduct(UUID.randomUUID(), "Laptop", "Powerful laptop", Product.ProductStatus.ACTIVE, null, null, 2.5, "15-inch", location);

        String details = product.showDetail();
        assertTrue(details.contains("Product Name: Laptop"));
        assertTrue(details.contains("Weight: 2.5 kg"));
        assertTrue(details.contains("Location: " + location.toString()));
    }
} 