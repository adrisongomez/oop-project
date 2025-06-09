package test.oopecommerce.utils.hasher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oopecommerce.utils.hasher.BcryptHasher;
import com.oopecommerce.utils.hasher.Hasher;

public class TestBcryptHasher {
    private final Hasher hasher = new BcryptHasher();

    @Test
    public void hashAndMatch() {
        String raw = "password123";
        String hashed = hasher.hash(raw);
        assertNotNull(hashed);
        assertTrue(hasher.match(raw, hashed));
        assertFalse(hasher.match("other", hashed));
    }
}
