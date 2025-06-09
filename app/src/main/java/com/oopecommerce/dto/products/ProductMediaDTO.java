package com.oopecommerce.dto.products;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product media details")
public class ProductMediaDTO {
    private UUID id;
    private String alt;
    private String url;
    private String type;
    private int position;

    public ProductMediaDTO() {}
    public ProductMediaDTO(UUID id, String alt, String url, String type, int position) {
        this.id = id;
        this.alt = alt;
        this.url = url;
        this.type = type;
        this.position = position;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
}
