package com.oopecommerce.models.products;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.oopecommerce.utils.sorts.SortDirection;
import com.oopecommerce.utils.sorts.SortableUtils;

public class Product {

    static public enum ProductStatus {
        DRAFT,
        ACTIVE,
        ARCHIVED,
    }

    final UUID id;
    String name;
    String description;
    ProductStatus status;
    List<ProductMedia> medias;
    List<ProductVariant> variants;

    public Product(UUID id, String name, String description, ProductStatus status,
            List<ProductMedia> medias, List<ProductVariant> variants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.setMedias(medias);
        this.setVariants(variants);
    }

    public Boolean equals(Product product) {
        return product != null && this.id.equals(product.id);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<ProductMedia> getMedias() {
        return medias;
    }

    public void setMedias(List<ProductMedia> medias) {
        try {
            this.medias = SortableUtils.sortByPosition(medias, SortDirection.ASC);
        } catch (Exception e) {
            this.medias = null;
        }
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        try {
            this.variants = SortableUtils.sortByPosition(variants, SortDirection.ASC);
        } catch (Exception error) {
            this.variants = new ArrayList<>();
        }
    }

    public String showDetail() {
        return "Product Name: " + this.name + "\nDescription: " + this.description + "\nStatus: " + this.status;
    }

    public double calculateShippingCost() {
        // Default shipping cost for a generic product
        return 5.00;
    }
}
