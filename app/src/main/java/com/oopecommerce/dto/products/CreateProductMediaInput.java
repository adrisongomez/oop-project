package com.oopecommerce.dto.products;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Media information for a new product")
public class CreateProductMediaInput {
    private String alt;
    private String url;
    private String type;
    private int position;

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
}
