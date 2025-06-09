package com.oopecommerce.dto.products;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Payload for creating a product.
 */
@Schema(description = "Payload for creating a new product")
public class CreateProductInput {
    @Schema(description = "Name of the product", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Description of the product")
    private String description;

    @Schema(description = "Media list for the product")
    private List<CreateProductMediaInput> medias;

    @Schema(description = "Variants for the product")
    private List<CreateProductVariantInput> variants;

    public CreateProductInput() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<CreateProductMediaInput> getMedias() { return medias; }
    public void setMedias(List<CreateProductMediaInput> medias) { this.medias = medias; }
    public List<CreateProductVariantInput> getVariants() { return variants; }
    public void setVariants(List<CreateProductVariantInput> variants) { this.variants = variants; }
}
