package com.oopecommerce.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oopecommerce.models.products.Product;
import com.oopecommerce.utils.HibernateUtil;

public class HibernateProductRepository implements ProductRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<Product> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Product.class, id));
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        }
    }

    @Override
    public void delete(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Product p = session.get(Product.class, id);
            if (p != null) session.remove(p);
            tx.commit();
        }
    }

    @Override
    public Iterable<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    @Override
    public Iterable<Product> search(String query, int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Product";
            if (query != null && !query.isEmpty()) {
                hql += " where lower(name) like :q or lower(description) like :q";
            }
            var ql = session.createQuery(hql, Product.class);
            if (query != null && !query.isEmpty()) {
                ql.setParameter("q", "%" + query.toLowerCase() + "%");
            }
            ql.setFirstResult(offset);
            ql.setMaxResults(limit);
            List<Product> list = ql.list();
            return list;
        }
    }
}
