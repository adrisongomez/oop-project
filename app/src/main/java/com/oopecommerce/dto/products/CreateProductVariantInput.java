package com.oopecommerce.dto.products;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Variant data for a new product")
public class CreateProductVariantInput {
    private String sku;
    private List<String> options;
    private int position;
    private double price;
    private String weightInGrams;
    private CreateProductMediaInput featureMedia;

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getWeightInGrams() { return weightInGrams; }
    public void setWeightInGrams(String weightInGrams) { this.weightInGrams = weightInGrams; }
    public CreateProductMediaInput getFeatureMedia() { return featureMedia; }
    public void setFeatureMedia(CreateProductMediaInput featureMedia) { this.featureMedia = featureMedia; }
}
