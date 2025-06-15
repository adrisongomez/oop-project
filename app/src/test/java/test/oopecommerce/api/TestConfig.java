package test.oopecommerce.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.oopecommerce.repositories.IUserRepository;
import test.oopecommerce.__fixtures__.InMemoryUserRepository;

@TestConfiguration
public class TestConfig {
    
    @Bean
    @Primary
    public IUserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}
