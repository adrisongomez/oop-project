package com.oopecommerce.dto.products;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload to patch a product variant")
public class PatchProductVariantInput {
    private String sku;
    private List<String> options;
    private Integer position;
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
