package com.oopecommerce.repositories;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.oopecommerce.models.inventory.InventoryLocation;

@Component
public class InMemoryInventoryLocationRepository implements InventoryLocationRepository {
    private final Map<UUID, InventoryLocation> locations = new ConcurrentHashMap<>();

    @Override
    public Optional<InventoryLocation> findById(UUID id) {
        return Optional.ofNullable(locations.get(id));
    }

    @Override
    public Iterable<InventoryLocation> findAll() {
        return new ArrayList<>(locations.values());
    }

    public void save(InventoryLocation loc) {
        locations.put(loc.getId(), loc);
    }
}
