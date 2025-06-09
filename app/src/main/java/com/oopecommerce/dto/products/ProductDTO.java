package com.oopecommerce.dto.products;

import java.util.UUID;

import com.oopecommerce.models.products.Product.ProductStatus;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO used to expose product information.
 */
@Schema(description = "Product details")
public class ProductDTO {
    @Schema(description = "Unique identifier of the product")
    private UUID id;

    @Schema(description = "Name of the product")
    private String name;

    @Schema(description = "Description of the product")
    private String description;

    @Schema(description = "Current status of the product")
    private ProductStatus status;

    public ProductDTO() {}

    public ProductDTO(UUID id, String name, String description, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}
