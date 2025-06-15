package com.oopecommerce.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.oopecommerce.models.users.User;
import com.oopecommerce.utils.HibernateUtil;

@Component
public class HibernateUserRepository implements IUserRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<User> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session
                    .createQuery("SELECT u FROM User u WHERE u.email = :email",
                            User.class)
                    .setParameter("email", email).getSingleResult());
        }
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    @Override
    public void delete(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            User u = session.get(User.class, id);
            if (u != null) {
                session.remove(u);
            }
            tx.commit();
        }
    }

    @Override
    public Iterable<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class)
                    .list();
        }
    }

    @Override
    public Iterable<User> search(String query, int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Product";
            if (query != null && !query.isEmpty()) {
                hql += " where lower(name) like :q or lower(email) like :q";
            }
            var ql = session.createQuery(hql, User.class);
            if (query != null && !query.isEmpty()) {
                ql.setParameter("", "%" + query.toLowerCase() + "%");
            }
            ql.setFirstResult(offset);
            ql.setMaxResults(limit);
            List<User> userList = ql.list();
            return userList;
        }
    }

}
