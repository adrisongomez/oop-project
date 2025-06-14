package com.oopecommerce.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.oopecommerce.dto.inventory.InventoryLevelDTO;
import com.oopecommerce.dto.inventory.UpdateInventoryLevelInput;
import com.oopecommerce.models.inventory.InventoryLevel;
import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.models.products.Product;
import com.oopecommerce.repositories.InventoryLevelRepository;
import com.oopecommerce.repositories.ProductRepository;
import com.oopecommerce.repositories.InventoryLocationRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/locations")
public class InventoryLevelController {
    private final Gson gson = new Gson();
    private final InventoryLevelRepository repository;
    private final ProductRepository productRepository;
    private final InventoryLocationRepository locationRepository;

    public InventoryLevelController(InventoryLevelRepository repository, ProductRepository productRepository,
            InventoryLocationRepository locationRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    private InventoryLevelDTO toDto(InventoryLevel level) {
        return new InventoryLevelDTO(level.getId(), level.getProduct().getId(), level.getLocation().getId(), level.getQuantity());
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listLevels(@PathVariable UUID productId) {
        Iterable<InventoryLevel> list = repository.findAllByProduct(productId);
        List<InventoryLevelDTO> out = new ArrayList<>();
        list.forEach(l -> out.add(toDto(l)));
        return gson.toJson(out);
    }

    @GetMapping(value = "/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getLevel(@PathVariable UUID productId, @PathVariable UUID locationId) {
        var level = repository.findByProductAndLocation(productId, locationId).orElse(null);
        if (level == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gson.toJson(toDto(level)));
    }

    @PutMapping(value = "/{locationId}")
    public ResponseEntity<String> setLevel(@PathVariable UUID productId, @PathVariable UUID locationId,
            @Valid @RequestBody UpdateInventoryLevelInput req) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        InventoryLocation loc = locationRepository.findById(locationId).orElse(null);
        if (loc == null) {
            return ResponseEntity.notFound().build();
        }
        InventoryLevel level = repository.findByProductAndLocation(productId, locationId).orElse(null);
        if (level == null) {
            level = new InventoryLevel(UUID.randomUUID(), product, loc, req.getQuantity());
        } else {
            level.setQuantity(req.getQuantity());
        }
        repository.save(level);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(gson.toJson(toDto(level)));
    }

    @DeleteMapping(value = "/{locationId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable UUID productId, @PathVariable UUID locationId) {
        InventoryLevel level = repository.findByProductAndLocation(productId, locationId).orElse(null);
        if (level == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(level.getId());
        return ResponseEntity.noContent().build();
    }
}
