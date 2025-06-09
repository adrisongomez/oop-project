package com.oopecommerce.api.auth;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oopecommerce.models.users.User;
import com.oopecommerce.repositories.UserRepository;
import com.oopecommerce.security.JwtTokenService;
import com.oopecommerce.security.SessionStore;
import com.oopecommerce.utils.hasher.BcryptHasher;
import com.oopecommerce.utils.hasher.Hasher;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository repository;
    private final JwtTokenService jwtService;
    private final SessionStore sessionStore;
    private final Hasher hasher = new BcryptHasher();

    public AuthController(UserRepository repository, JwtTokenService jwtService, SessionStore sessionStore) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.sessionStore = sessionStore;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest req, HttpServletRequest request) {
        User user = repository.findByEmail(req.getEmail()).orElse(null);
        if (user == null || !hasher.match(req.getPassword(), user.getHashedPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtService.generateToken(user, new String[] { "read_user" });
        sessionStore.record(user, request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
