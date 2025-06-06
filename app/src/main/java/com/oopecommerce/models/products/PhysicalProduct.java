package com.oopecommerce.models.products;

import java.util.List;
import java.util.UUID;

import com.oopecommerce.models.inventory.InventoryLocation;

public class PhysicalProduct extends Product {

    private double weight;
    private String dimensions;
    private InventoryLocation inventoryLocation;

    public PhysicalProduct(UUID id, String name, String description, ProductStatus status,
                           List<ProductMedia> medias, List<ProductVariant> variants,
                           double weight, String dimensions, InventoryLocation inventoryLocation) {
        super(id, name, description, status, medias, variants);
        this.weight = weight;
        this.dimensions = dimensions;
        this.inventoryLocation = inventoryLocation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public InventoryLocation getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(InventoryLocation inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public boolean equals(PhysicalProduct product) {
        return super.equals(product) &&
               this.weight == product.getWeight() &&
               this.dimensions.equals(product.getDimensions()) &&
               this.inventoryLocation.equals(product.getInventoryLocation());
    }
} 