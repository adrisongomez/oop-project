package com.oopecommerce.dto.products;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload to patch a product media")
public class PatchProductMediaInput {
    private String alt;
    private String url;
    private String type;
    private Integer position;

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
}
