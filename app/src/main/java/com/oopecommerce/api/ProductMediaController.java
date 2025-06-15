package com.oopecommerce.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oopecommerce.dto.products.CreateProductMediaInput;
import com.oopecommerce.dto.products.ProductMediaDTO;
import com.oopecommerce.dto.products.UpdateProductMediaInput;
import com.oopecommerce.dto.products.PatchProductMediaInput;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.ProductMedia;
import com.oopecommerce.repositories.IProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/media")
public class ProductMediaController {
    private final IProductRepository repository;

    public ProductMediaController(IProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductMediaDTO> create(@PathVariable UUID productId,
            @Valid @RequestBody CreateProductMediaInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductMedia media = new ProductMedia(UUID.randomUUID(), req.getAlt(), req.getUrl(), req.getType(),
                req.getPosition());
        media.setProduct(product);
        product.getMedias().add(media);
        repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(toDto(media));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductMediaDTO> get(@PathVariable UUID productId, @PathVariable UUID id) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductMedia m : product.getMedias()) {
            if (m.getId().equals(id)) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(m));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductMediaDTO> update(@PathVariable UUID productId, @PathVariable UUID id,
            @Valid @RequestBody UpdateProductMediaInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductMedia m : product.getMedias()) {
            if (m.getId().equals(id)) {
                m.setPosition(req.getPosition());
                // alt, url, type
                m.setAlt(req.getAlt());
                m.setUrl(req.getUrl());
                m.setType(req.getType());
                repository.save(product);
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(m));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductMediaDTO> patch(@PathVariable UUID productId, @PathVariable UUID id,
            @Valid @RequestBody PatchProductMediaInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductMedia m : product.getMedias()) {
            if (m.getId().equals(id)) {
                if (req.getAlt() != null)
                    m.setAlt(req.getAlt());
                if (req.getUrl() != null)
                    m.setUrl(req.getUrl());
                if (req.getType() != null)
                    m.setType(req.getType());
                if (req.getPosition() != null)
                    m.setPosition(req.getPosition());
                repository.save(product);
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(m));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId, @PathVariable UUID id) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        boolean removed = product.getMedias().removeIf(m -> m.getId().equals(id));
        if (removed) {
            repository.save(product);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ProductMediaDTO toDto(ProductMedia m) {
        return new ProductMediaDTO(m.getId(), m.getAlt(), m.getUrl(), m.getType(), m.getPosition());
    }
}
