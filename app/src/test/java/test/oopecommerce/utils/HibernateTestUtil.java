package test.oopecommerce.utils;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.oopecommerce.models.users.User;

public class HibernateTestUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.username", "sa");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "none");

        Flyway.configure()
            .dataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "")
            .load()
            .migrate();

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
    }
}
