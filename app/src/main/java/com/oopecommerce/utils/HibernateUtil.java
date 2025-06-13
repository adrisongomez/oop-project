package com.oopecommerce.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.flywaydb.core.Flyway;
import com.oopecommerce.models.users.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
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

            Flyway flyway = Flyway.configure()
                .dataSource(
                    cfg.getProperty("hibernate.connection.url"),
                    cfg.getProperty("hibernate.connection.username"),
                    cfg.getProperty("hibernate.connection.password"))
                .baselineOnMigrate(true)
                .createSchemas(true)
                .locations("classpath:db/migration")
                .load();
            
            try {
                logger.info("Attempting database migration with URL: {}", cfg.getProperty("hibernate.connection.url"));
                flyway.migrate();
                logger.info("Migration completed successfully");
            } catch (Exception e) {
                logger.error("Migration failed: {}", e.getMessage(), e);
                throw e;
            }

            cfg.addAnnotatedClass(User.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.products.Product.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.products.ProductMedia.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.products.ProductVariant.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.products.SimpleProduct.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.addresses.ShippingAddress.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.carts.Cart.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.carts.CartLineItem.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.inventory.InventoryLocation.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.inventory.InventoryLevel.class);
            cfg.addAnnotatedClass(com.oopecommerce.models.payments.Payment.class);
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
