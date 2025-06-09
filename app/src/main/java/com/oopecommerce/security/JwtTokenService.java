package com.oopecommerce.security;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.oopecommerce.models.users.User;

@Component
public class JwtTokenService implements TokenService {
    private final Algorithm algorithm;

    public JwtTokenService() {
        String secret = System.getenv().getOrDefault("JWT_SECRET", "secret");
        this.algorithm = Algorithm.HMAC256(secret);
    }

    @Override
    public String generateToken(User user, String[] claims) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withArrayClaim("claims", claims)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(30 * 60)))
                .sign(algorithm);
    }

    @Override
    public UserData verify(String token) throws Exception {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        List<String> claims = Arrays.asList(jwt.getClaim("claims").asArray(String.class));
        return new UserData(UUID.fromString(jwt.getSubject()), jwt.getClaim("email").asString(), claims);
    }
}
