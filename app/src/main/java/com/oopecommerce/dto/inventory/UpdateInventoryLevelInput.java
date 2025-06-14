package com.oopecommerce.dto.inventory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(description = "Information required to set inventory quantity")
public class UpdateInventoryLevelInput {
    @Min(0)
    private int quantity;

    public UpdateInventoryLevelInput() {}

    public UpdateInventoryLevelInput(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
