package test.oopecommerce.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oopecommerce.config.ConfiguracionSistema;

public class TestConfiguracionSistema {

    @Test
    public void singletonReturnsSameInstance() {
        ConfiguracionSistema c1 = ConfiguracionSistema.getInstance();
        ConfiguracionSistema c2 = ConfiguracionSistema.getInstance();
        assertSame(c1, c2);
    }
}
