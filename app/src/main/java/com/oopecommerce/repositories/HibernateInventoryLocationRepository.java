package com.oopecommerce.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.oopecommerce.models.inventory.InventoryLocation;
import com.oopecommerce.utils.HibernateUtil;

@Component
public class HibernateInventoryLocationRepository implements InventoryLocationRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<InventoryLocation> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(InventoryLocation.class, id));
        }
    }

    @Override
    public Iterable<InventoryLocation> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<InventoryLocation> list = session.createQuery("from InventoryLocation", InventoryLocation.class).list();
            return list;
        }
    }
}
