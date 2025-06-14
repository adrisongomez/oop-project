package com.oopecommerce.repositories;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.oopecommerce.models.inventory.InventoryLevel;

@Component
public class InMemoryInventoryLevelRepository implements InventoryLevelRepository {
    private final Map<UUID, InventoryLevel> levels = new ConcurrentHashMap<>();

    @Override
    public Optional<InventoryLevel> findById(UUID id) {
        return Optional.ofNullable(levels.get(id));
    }

    @Override
    public Optional<InventoryLevel> findByProductAndLocation(UUID productId, UUID locationId) {
        return levels.values().stream()
            .filter(l -> l.getProduct().getId().equals(productId) && l.getLocation().getId().equals(locationId))
            .findFirst();
    }

    @Override
    public void save(InventoryLevel level) {
        levels.put(level.getId(), level);
    }

    @Override
    public void delete(UUID id) {
        levels.remove(id);
    }

    @Override
    public Iterable<InventoryLevel> findAllByProduct(UUID productId) {
        List<InventoryLevel> list = new ArrayList<>();
        levels.values().forEach(l -> { if (l.getProduct().getId().equals(productId)) list.add(l); });
        return list;
    }
}
