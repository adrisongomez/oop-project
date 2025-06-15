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

import com.oopecommerce.dto.products.CreateProductVariantInput;
import com.oopecommerce.dto.products.ProductVariantDTO;
import com.oopecommerce.dto.products.UpdateProductVariantInput;
import com.oopecommerce.dto.products.PatchProductVariantInput;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.ProductMedia;
import com.oopecommerce.models.products.ProductVariant;
import com.oopecommerce.repositories.IProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/variants")
public class ProductVariantController {
    private final IProductRepository repository;

    public ProductVariantController(IProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductVariantDTO> create(@PathVariable UUID productId,
            @Valid @RequestBody CreateProductVariantInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductMedia fm = null;
        if (req.getFeatureMedia() != null) {
            fm = new ProductMedia(UUID.randomUUID(), req.getFeatureMedia().getAlt(), req.getFeatureMedia().getUrl(),
                    req.getFeatureMedia().getType(), req.getFeatureMedia().getPosition());
            product.getMedias().add(fm);
            fm.setProduct(product);
        }
        ProductVariant variant = new ProductVariant(UUID.randomUUID(), req.getSku(), req.getOptions(),
                req.getPosition(), fm, req.getPrice(), req.getWeightInGrams());
        product.getVariants().add(variant);
        variant.setProduct(product);
        repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(toDto(variant));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductVariantDTO> get(@PathVariable UUID productId, @PathVariable UUID id) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductVariant v : product.getVariants()) {
            if (v.getId().equals(id)) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(v));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductVariantDTO> update(@PathVariable UUID productId, @PathVariable UUID id,
            @Valid @RequestBody UpdateProductVariantInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductVariant v : product.getVariants()) {
            if (v.getId().equals(id)) {
                v.setSku(req.getSku());
                v.setOptions(req.getOptions());
                v.setPosition(req.getPosition());
                v.setPrice(req.getPrice());
                v.setWeightInGrams(req.getWeightInGrams());
                repository.save(product);
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(v));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductVariantDTO> patch(@PathVariable UUID productId, @PathVariable UUID id,
            @Valid @RequestBody PatchProductVariantInput req) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        for (ProductVariant v : product.getVariants()) {
            if (v.getId().equals(id)) {
                if (req.getSku() != null)
                    v.setSku(req.getSku());
                if (req.getOptions() != null)
                    v.setOptions(req.getOptions());
                if (req.getPosition() != null)
                    v.setPosition(req.getPosition());
                if (req.getPrice() != null)
                    v.setPrice(req.getPrice());
                if (req.getWeightInGrams() != null)
                    v.setWeightInGrams(req.getWeightInGrams());
                repository.save(product);
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(toDto(v));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable UUID productId, @PathVariable UUID id) {
        Product product = repository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        boolean removed = product.getVariants().removeIf(v -> v.getId().equals(id));
        if (removed) {
            repository.save(product);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ProductVariantDTO toDto(ProductVariant v) {
        UUID feature = v.getFeatureMedia() != null ? v.getFeatureMedia().getId() : null;
        return new ProductVariantDTO(v.getId(), v.getSku(), v.getOptions(), v.getPosition(), feature, v.getPrice(),
                v.getWeightInGrams());
    }
}
