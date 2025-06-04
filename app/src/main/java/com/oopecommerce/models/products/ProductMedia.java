package com.oopecommerce.models.products;

import java.util.UUID;

import com.oopecommerce.utils.sorts.ISortable;

public class ProductMedia implements ISortable {

    final UUID id;
    final String alt;
    final String url;
    final String type;

    int position;

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

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}