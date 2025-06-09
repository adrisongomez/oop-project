package test.oopecommerce.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oopecommerce.models.users.User;
import test.oopecommerce.utils.HibernateTestUtil;

import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserHibernateIntegrationTest {
    private SessionFactory sessionFactory;

    @BeforeAll
    public void setup() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

    @AfterAll
    public void teardown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void persistAndRetrieveUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "integration@example.com", "pass", "Integration");

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }

        User loaded;
        try (Session readSession = sessionFactory.openSession()) {
            loaded = readSession.get(User.class, id);
        }

        assertEquals("integration@example.com", loaded.getEmail());
    }
}
