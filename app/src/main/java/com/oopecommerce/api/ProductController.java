package com.oopecommerce.api;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.google.gson.Gson;
import com.oopecommerce.dto.products.CreateProductInput;
import com.oopecommerce.dto.products.UpdateProductInput;
import com.oopecommerce.dto.products.PatchProductInput;
import com.oopecommerce.dto.products.ProductDTO;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.models.products.Product.ProductStatus;
import com.oopecommerce.models.products.SimpleProduct;
import com.oopecommerce.models.products.ProductMedia;
import com.oopecommerce.models.products.ProductVariant;
import com.oopecommerce.repositories.IProductRepository;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    private final Gson gson = new Gson();
    private final IProductRepository repository;

    public ProductController(IProductRepository repository) {
        this.repository = repository;
    }

    private ProductDTO toDto(Product p) {
        return new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getStatus());
    }

    @PostMapping("/")
    @Operation(summary = "Create a new product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductInput req) {
        UUID id = UUID.randomUUID();
        List<ProductMedia> medias = new ArrayList<>();
        if (req.getMedias() != null) {
            for (var m : req.getMedias()) {
                medias.add(new ProductMedia(UUID.randomUUID(), m.getAlt(), m.getUrl(), m.getType(), m.getPosition()));
            }
        }
        List<ProductVariant> variants = new ArrayList<>();
        if (req.getVariants() != null) {
            for (var v : req.getVariants()) {
                ProductMedia fm = null;
                if (v.getFeatureMedia() != null) {
                    fm = new ProductMedia(UUID.randomUUID(), v.getFeatureMedia().getAlt(), v.getFeatureMedia().getUrl(), v.getFeatureMedia().getType(), v.getFeatureMedia().getPosition());
                    medias.add(fm);
                }
                variants.add(new ProductVariant(UUID.randomUUID(), v.getSku(), v.getOptions(), v.getPosition(), fm, v.getPrice(), v.getWeightInGrams()));
            }
        }
        Product product = new SimpleProduct(id, req.getName(), req.getDescription(), ProductStatus.DRAFT);
        product.setMedias(medias);
        product.setVariants(variants);
        repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(product)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public ResponseEntity<String> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateProductInput req) {
        Product existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setName(req.getName());
        existing.setDescription(req.getDescription());
        existing.setStatus(req.getStatus());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(existing)));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an existing product")
    public ResponseEntity<String> patchProduct(@PathVariable UUID id, @Valid @RequestBody PatchProductInput req) {
        Product existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (req.getName() != null) {
            existing.setName(req.getName());
        }
        if (req.getDescription() != null) {
            existing.setDescription(req.getDescription());
        }
        if (req.getStatus() != null) {
            existing.setStatus(req.getStatus());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(existing)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by its ID")
    public ResponseEntity<String> getProduct(@PathVariable UUID id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(product)));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List products with optional query and pagination")
    public String listProducts(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int page) {
        int offset = (page - 1) * pageSize;
        Iterable<Product> found = repository.search(q, pageSize, offset);
        List<ProductDTO> list = new ArrayList<>();
        found.forEach(p -> list.add(toDto(p)));
        return gson.toJson(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by its ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        if (repository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
