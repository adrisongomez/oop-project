package com.oopecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.inventory.InventoryLevel;

public interface InventoryLevelRepository {
    Optional<InventoryLevel> findById(UUID id);
    Optional<InventoryLevel> findByProductAndLocation(UUID productId, UUID locationId);
    void save(InventoryLevel level);
    void delete(UUID id);
    Iterable<InventoryLevel> findAllByProduct(UUID productId);
}
