package test.oopecommerce.models.inventory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.oopecommerce.models.inventory.InventoryLocation;

public class TestInventoryLocation {

    @Test
    public void testInventoryLocationEquals() {
        InventoryLocation loc1 = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        InventoryLocation loc2 = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        InventoryLocation loc3 = new InventoryLocation("WH2", "456 Oak Ave", "CityB", "CountryB");
        InventoryLocation loc4 = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryC");

        assertTrue(loc1.equals(loc2));
        assertFalse(loc1.equals(loc3));
        assertFalse(loc1.equals(loc4));
    }

    @Test
    public void testGettersAndSetters() {
        InventoryLocation loc = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");

        loc.setWarehouseCode("WH2");
        assertEquals("WH2", loc.getWarehouseCode());

        loc.setStreetAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", loc.getStreetAddress());

        loc.setCity("CityB");
        assertEquals("CityB", loc.getCity());

        loc.setCountry("CountryB");
        assertEquals("CountryB", loc.getCountry());
    }

    @Test
    public void testToString() {
        InventoryLocation loc = new InventoryLocation("WH1", "123 Main St", "CityA", "CountryA");
        String expected = "InventoryLocation{" +
                "warehouseCode='WH1'" +
                ", streetAddress='123 Main St'" +
                ", city='CityA'" +
                ", country='CountryA'" +
                '}';
        assertEquals(expected, loc.toString());
    }
} 