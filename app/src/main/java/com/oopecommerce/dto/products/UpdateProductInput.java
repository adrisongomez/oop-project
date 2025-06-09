package com.oopecommerce.dto.products;

import com.oopecommerce.models.products.Product.ProductStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Payload for updating a product.
 */
@Schema(description = "Payload for updating an existing product")
public class UpdateProductInput {
    @Schema(description = "Updated name", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Updated description")
    private String description;

    @Schema(description = "Updated status", required = true)
    @NotNull
    private ProductStatus status;

    public UpdateProductInput() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}
