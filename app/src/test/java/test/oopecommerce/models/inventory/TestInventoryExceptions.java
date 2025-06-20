package test.oopecommerce.models.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oopecommerce.exceptions.InventoryInsufficientException;
import com.oopecommerce.models.inventory.PhysicalInventoryManager;
import com.oopecommerce.models.products.SimpleProduct;
import com.oopecommerce.models.products.Product.ProductStatus;

import java.util.UUID;

public class TestInventoryExceptions {
    @Test
    public void updateStockThrowsWhenInsufficient() {
        PhysicalInventoryManager manager = new PhysicalInventoryManager();
        SimpleProduct product = new SimpleProduct(UUID.randomUUID(), "prod", "desc", ProductStatus.ACTIVE);
        manager.addProduct(product, 1);

        assertThrows(InventoryInsufficientException.class, () -> {
            manager.updateStock(product, 5);
        });
    }
}
