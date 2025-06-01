package test.graphlog.auth.models;

import com.graphlog.auth.models.Role;
import com.graphlog.auth.models.Role.Claim;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

public class RoleTest {
    @Test public void claimEquals() {
        UUID id = UUID.randomUUID();
        Claim claim = new Role.Claim(
            id,
            "my claim"
        );
        Claim claim2 = new Role.Claim(
            id,
            "my claim2"
        );
        assertTrue(claim.equals(claim2));
    }

    @Test public void claimNotEquals() {
        Claim claim = new Claim(
            UUID.randomUUID(),
            "my claim"
        );
        Claim claim2 = new Claim(
            UUID.randomUUID(),
            "my claim2"
        );
        assertFalse(claim.equals(claim2));
    }

    @Test public void roleNotEquals() {
        ArrayList<Claim> claims = new ArrayList<>();
        Role role = new Role(
            UUID.randomUUID(),
            "Role 1",
            claims
        );
        Role role2 = new Role(
            UUID.randomUUID(),
            "Role 2",
            claims
        );

        assertFalse(role.equals(role2));
    }

    @Test public void roleEquals() {
        ArrayList<Claim> claims = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Role role = new Role(
            id,
            "Role 1",
            claims
        );
        Role role2 = new Role(
            id,
            "Role 2",
            claims
        );

        assertTrue(role.equals(role2));
    }

    @Test public void RoleisAllowedTo() {
        ArrayList<Claim> claims = new ArrayList<>();

        Claim claim1 = new Claim(
            UUID.randomUUID(),
            "claim 1"
        );
        Claim claim2 = new Claim(
            UUID.randomUUID(),
            "claim 2"
        );
        claims.add(claim1);
        claims.add(claim2);

        UUID id = UUID.randomUUID();
        Role role = new Role(
            id,
            "Role 1",
            claims
        );
        assertTrue(role.isAllowedTo(claim2));
    }
}
