package com.oopecommerce.repositories;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.oopecommerce.models.products.Product;

@Component
public class InMemoryProductRepository implements ProductRepository {
    private final Map<UUID, Product> products = new ConcurrentHashMap<>();

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void delete(UUID id) {
        products.remove(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return products.values();
    }

    @Override
    public Iterable<Product> search(String query, int limit, int offset) {
        List<Product> results = new ArrayList<>();
        if (query != null) {
            String q = query.toLowerCase();
            for (Product p : products.values()) {
                if (p.getName().toLowerCase().contains(q) ||
                    (p.getDescription() != null && p.getDescription().toLowerCase().contains(q))) {
                    results.add(p);
                }
            }
        } else {
            results.addAll(products.values());
        }
        int start = Math.max(0, offset);
        int end = Math.min(results.size(), start + limit);
        if (start > end) {
            return new ArrayList<>();
        }
        return results.subList(start, end);
    }
}
