package com.oopecommerce.models.products;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.oopecommerce.utils.sorts.SortDirection;
import com.oopecommerce.utils.sorts.SortableUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    static public enum ProductStatus {
        DRAFT,
        ACTIVE,
        ARCHIVED,
    }

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductMedia> medias;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants;

    protected Product() {
        // Required by Hibernate
    }

    public Product(UUID id, String name, String description, ProductStatus status,
            List<ProductMedia> medias, List<ProductVariant> variants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.setMedias(medias);
        this.setVariants(variants);
        if (this.medias != null) {
            this.medias.forEach(m -> m.setProduct(this));
        }
        if (this.variants != null) {
            this.variants.forEach(v -> v.setProduct(this));
        }
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
            if (this.medias != null) {
                this.medias.forEach(m -> m.setProduct(this));
            }
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
            if (this.variants != null) {
                this.variants.forEach(v -> v.setProduct(this));
            }
        } catch (Exception error) {
            this.variants = new ArrayList<>();
        }
    }

    public String showDetail() {
        return "Product Name: " + this.name + "\nDescription: " + this.description + "\nStatus: " + this.status;
    }

    public abstract double calculateShippingCost();
}
