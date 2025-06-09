package com.oopecommerce.dto.products;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Payload to update a product media")
public class UpdateProductMediaInput {
    @NotBlank
    private String alt;
    @NotBlank
    private String url;
    @NotBlank
    private String type;
    @NotNull
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
