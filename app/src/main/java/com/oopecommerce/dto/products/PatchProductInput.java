package com.oopecommerce.dto.products;

import com.oopecommerce.models.products.Product.ProductStatus;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Payload for partially updating a product.
 */
@Schema(description = "Payload for partially updating a product")
public class PatchProductInput {
    @Schema(description = "Updated name")
    private String name;

    @Schema(description = "Updated description")
    private String description;

    @Schema(description = "Updated status")
    private ProductStatus status;

    public PatchProductInput() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}
