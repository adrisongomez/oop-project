package com.oopecommerce.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oopecommerce.models.inventory.InventoryLevel;
import com.oopecommerce.utils.HibernateUtil;

public class HibernateInventoryLevelRepository implements InventoryLevelRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<InventoryLevel> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(InventoryLevel.class, id));
        }
    }

    @Override
    public Optional<InventoryLevel> findByProductAndLocation(UUID productId, UUID locationId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from InventoryLevel where product.id = :pid and location.id = :lid";
            InventoryLevel level = session.createQuery(hql, InventoryLevel.class)
                    .setParameter("pid", productId)
                    .setParameter("lid", locationId)
                    .uniqueResult();
            return Optional.ofNullable(level);
        }
    }

    @Override
    public void save(InventoryLevel level) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(level);
            tx.commit();
        }
    }

    @Override
    public void delete(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            InventoryLevel l = session.get(InventoryLevel.class, id);
            if (l != null) session.remove(l);
            tx.commit();
        }
    }

    @Override
    public Iterable<InventoryLevel> findAllByProduct(UUID productId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from InventoryLevel where product.id = :pid";
            List<InventoryLevel> list = session.createQuery(hql, InventoryLevel.class)
                    .setParameter("pid", productId)
                    .list();
            return list;
        }
    }
}
