package com.oopecommerce.factories;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.*;
import com.oopecommerce.models.users.*;

/**
 * Fabrica de entidades para simplificar la creacion de productos y usuarios.
 */
public class FabricaEntidades {

    public static Product crearProducto(
            String tipo,
            UUID id,
            String nombre,
            String descripcion,
            Product.ProductStatus status,
            List<ProductMedia> medias,
            List<ProductVariant> variantes,
            Map<String, Object> extra
    ) {
        if ("digital".equalsIgnoreCase(tipo)) {
            String formato = (String) extra.get("formato");
            double tamano = (Double) extra.getOrDefault("tamano", 0.0);
            String storage = (String) extra.get("almacen");
            return new DigitalProduct(id, nombre, descripcion, status, medias, variantes, formato, tamano, storage);
        } else if ("fisico".equalsIgnoreCase(tipo)) {
            double peso = (Double) extra.getOrDefault("peso", 0.0);
            String dimensiones = (String) extra.get("dimensiones");
            InventoryLocation loc = (InventoryLocation) extra.get("ubicacion");
            return new PhysicalProduct(id, nombre, descripcion, status, medias, variantes, peso, dimensiones, loc);
        }
        throw new IllegalArgumentException("Tipo de producto no soportado: " + tipo);
    }

    public static User crearUsuario(
            String tipo,
            UUID id,
            String email,
            String hashedPassword,
            String nombre,
            Map<String, Object> extra
    ) {
        if ("cliente".equalsIgnoreCase(tipo)) {
            String preferencias = (String) extra.getOrDefault("preferencias", "");
            return new Customer(id, email, hashedPassword, nombre, preferencias);
        } else if ("administrador".equalsIgnoreCase(tipo) || "admin".equalsIgnoreCase(tipo)) {
            return new Admin(id, email, hashedPassword, nombre);
        }
        throw new IllegalArgumentException("Tipo de usuario no soportado: " + tipo);
    }
}
