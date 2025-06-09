package com.oopecommerce.dto.products;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Payload to update a product variant")
public class UpdateProductVariantInput {
    @NotBlank
    private String sku;
    private List<String> options;
    @NotNull
    private Integer position;
    @NotNull
    private Double price;
    private String weightInGrams;

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getWeightInGrams() { return weightInGrams; }
    public void setWeightInGrams(String weightInGrams) { this.weightInGrams = weightInGrams; }
}
