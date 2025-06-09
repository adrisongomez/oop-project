package com.oopecommerce.dto.products;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of a product variant")
public class ProductVariantDTO {
    private UUID id;
    private String sku;
    private List<String> options;
    private int position;
    private UUID featureMediaId;
    private double price;
    private String weightInGrams;

    public ProductVariantDTO() {}
    public ProductVariantDTO(UUID id, String sku, List<String> options, int position, UUID featureMediaId, double price, String weightInGrams) {
        this.id = id;
        this.sku = sku;
        this.options = options;
        this.position = position;
        this.featureMediaId = featureMediaId;
        this.price = price;
        this.weightInGrams = weightInGrams;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public UUID getFeatureMediaId() { return featureMediaId; }
    public void setFeatureMediaId(UUID featureMediaId) { this.featureMediaId = featureMediaId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getWeightInGrams() { return weightInGrams; }
    public void setWeightInGrams(String weightInGrams) { this.weightInGrams = weightInGrams; }
}
