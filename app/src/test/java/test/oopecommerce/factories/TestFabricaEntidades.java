package test.oopecommerce.factories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.oopecommerce.factories.FabricaEntidades;
import com.oopecommerce.models.products.DigitalProduct;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.Product.ProductStatus;
import com.oopecommerce.models.users.Customer;
import com.oopecommerce.models.users.User;

public class TestFabricaEntidades {

    @Test
    public void createDigitalProduct() {
        Map<String, Object> extra = new HashMap<>();
        extra.put("formato", "PDF");
        extra.put("tamano", 1.0);
        extra.put("almacen", "/tmp");
        Product p = FabricaEntidades.crearProducto(
                "digital",
                UUID.randomUUID(),
                "Ebook",
                "Desc",
                ProductStatus.ACTIVE,
                null,
                null,
                extra);
        assertTrue(p instanceof DigitalProduct);
    }

    @Test
    public void createCustomer() {
        Map<String, Object> extra = new HashMap<>();
        extra.put("preferencias", "ofertas");
        User u = FabricaEntidades.crearUsuario(
                "cliente",
                UUID.randomUUID(),
                "a@a.com",
                "hash",
                "Ana",
                extra);
        assertTrue(u instanceof Customer);
    }
}
