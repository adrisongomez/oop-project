package com.oopecommerce.dto.inventory;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Quantity of a product stored at a specific location")
public class InventoryLevelDTO {
    private UUID id;
    private UUID productId;
    private UUID locationId;
    private int quantity;

    public InventoryLevelDTO() {}

    public InventoryLevelDTO(UUID id, UUID productId, UUID locationId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.locationId = locationId;
        this.quantity = quantity;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public UUID getLocationId() { return locationId; }
    public void setLocationId(UUID locationId) { this.locationId = locationId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
