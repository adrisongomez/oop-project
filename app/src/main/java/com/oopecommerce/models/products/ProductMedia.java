package com.oopecommerce.models.products;

import java.util.UUID;

import com.oopecommerce.utils.sorts.ISortable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "product_media")
public class ProductMedia implements ISortable {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "alt")
    private String alt;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

    @Column(name = "position")
    private int position;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected ProductMedia() {
        // Required by Hibernate
    }

    public ProductMedia(UUID id, String alt, String url, String type, int position) {
        this.id = id;
        this.alt = alt;
        this.url = url;
        this.type = type;
        this.position = position;
    }

    public Boolean equals(ProductMedia media) {
        return media != null && media.id.equals(this.id);
    }

    public UUID getId() {
        return id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}