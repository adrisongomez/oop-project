package test.oopecommerce.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.oopecommerce.repositories.IUserRepository;
import com.oopecommerce.repositories.IProductRepository;
import com.oopecommerce.repositories.InventoryLevelRepository;
import com.oopecommerce.repositories.InventoryLocationRepository;
import test.oopecommerce.__fixtures__.InMemoryUserRepository;
import test.oopecommerce.__fixtures__.InMemoryProductRepository;
import test.oopecommerce.__fixtures__.InMemoryInventoryLevelRepository;
import test.oopecommerce.__fixtures__.InMemoryInventoryLocationRepository;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public IUserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public IProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    public InventoryLevelRepository inventoryLevelRepository() {
        return new InMemoryInventoryLevelRepository();
    }

    @Bean
    public InventoryLocationRepository inventoryLocationRepository() {
        return new InMemoryInventoryLocationRepository();
    }
}
