package com.oopecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.inventory.InventoryLocation;

public interface InventoryLocationRepository {
    Optional<InventoryLocation> findById(UUID id);
    Iterable<InventoryLocation> findAll();
}
