package com.oopecommerce.models.products;

import java.util.List;
import java.util.UUID;

import com.oopecommerce.utils.sorts.ISortable;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_variants")
public class ProductVariant implements ISortable {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "sku")
    private String sku;

    @ElementCollection
    @Column(name = "option_value")
    private List<String> options;

    @Column(name = "position")
    private int position;

    @OneToOne
    @JoinColumn(name = "feature_media_id")
    private ProductMedia featureMedia;

    @Column(name = "price")
    private double price;

    @Column(name = "weight_in_grams")
    private String weightInGrams;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected ProductVariant() {
        // Required by Hibernate
    }

    public ProductVariant(UUID id, String sku, List<String> options, int position, ProductMedia featureMedia,
            double price, String weightInGrams) {
        this.id = id;
        this.sku = sku;
        this.options = options;
        this.setPosition(position);
        this.featureMedia = featureMedia;
        this.setPrice(price);
        this.weightInGrams = weightInGrams;
    }

    public Boolean equals(ProductVariant variant) {
        return variant != null && this.id.equals(variant.id);
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

    public void setPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position cannot be negative.");
        }
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

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public String getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
