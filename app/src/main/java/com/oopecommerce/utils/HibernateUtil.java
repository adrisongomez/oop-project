package com.oopecommerce.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

public class HibernateUtil {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            Configuration cfg = new Configuration().configure();
            overrideProperty(cfg, "hibernate.connection.url", "HIBERNATE_CONN_URL");
            overrideProperty(cfg, "hibernate.connection.username", "HIBERNATE_CONN_USERNAME");
            overrideProperty(cfg, "hibernate.connection.password", "HIBERNATE_CONN_PASSWORD");
            overrideProperty(cfg, "hibernate.dialect", "HIBERNATE_DIALECT");
            overrideProperty(cfg, "hibernate.hbm2ddl.auto", "HIBERNATE_HBM2DDL_AUTO");
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void overrideProperty(Configuration cfg, String propertyName, String envVar) {
        String value = dotenv.get(envVar);
        if (value == null || value.isEmpty()) {
            value = System.getenv(envVar);
        }
        if (value != null && !value.isEmpty()) {
            cfg.setProperty(propertyName, value);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
