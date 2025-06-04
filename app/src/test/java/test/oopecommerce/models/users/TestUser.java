package test.oopecommerce.models.users;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.oopecommerce.models.users.User;

public class TestUser {
    @Test
    public void testEqualsMethod() {
        UUID id = UUID.randomUUID();
        User user1 = new User(id, "a@example.com", "hash1", "A");
        User user2 = new User(id, "b@example.com", "hash2", "B");
        User user3 = new User(UUID.randomUUID(), "c@example.com", "hash3", "C");

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
    }
}
