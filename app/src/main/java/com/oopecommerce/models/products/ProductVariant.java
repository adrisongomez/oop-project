package com.oopecommerce.models.products;

import java.util.List;
import java.util.UUID;

import com.oopecommerce.utils.sorts.ISortable;

public class ProductVariant implements ISortable {
    final UUID id;
    String sku;
    List<String> options;
    int position;
    ProductMedia featureMedia;
    double price;
    String weightInGrams;

    public ProductVariant(UUID id, String sku, List<String> options, int position, ProductMedia featureMedia,
            double price, String weightInGrams) {
        this.id = id;
        this.sku = sku;
        this.options = options;
        this.position = position;
        this.featureMedia = featureMedia;
        this.price = price;
        this.weightInGrams = weightInGrams;
    }

    public Boolean equals(ProductVariant variant) {
        return id.equals(variant.id);
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ProductMedia getFeatureMedia() {
        return featureMedia;
    }

    public void setFeatureMedia(ProductMedia featureMedia) {
        this.featureMedia = featureMedia;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    // Here we are going to do the stock computes
}
