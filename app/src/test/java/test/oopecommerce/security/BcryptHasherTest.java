package test.oopecommerce.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oopecommerce.security.BcryptHasher;
import com.oopecommerce.security.IHasher;

public class BcryptHasherTest {
    @Test
    public void testHashAndMatch() {
        IHasher hasher = new BcryptHasher("SECRET");
        String hashed = hasher.hash("password");
        assertNotNull(hashed);
        assertTrue(hasher.match("password", hashed));
        assertFalse(hasher.match("other", hashed));
    }
}
