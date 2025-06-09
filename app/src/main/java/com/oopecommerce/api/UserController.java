package com.oopecommerce.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.oopecommerce.dto.users.UserDTO;
import com.oopecommerce.dto.users.CreateUserInput;
import com.oopecommerce.dto.users.UpdateUserInput;
import com.oopecommerce.dto.users.PatchUserInput;
import com.oopecommerce.models.users.User;
import com.google.gson.Gson;
import com.oopecommerce.utils.hasher.Hasher;
import com.oopecommerce.utils.hasher.BcryptHasher;
import com.oopecommerce.repositories.UserRepository;
import com.oopecommerce.security.JwtTokenService;
import com.oopecommerce.security.SessionStore;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations related to application users")
public class UserController {

    private final Gson gson = new Gson();
    private final Hasher hasher = new BcryptHasher();
    private final UserRepository repository;
    private final JwtTokenService jwtService;
    private final SessionStore sessionStore;

    public UserController(UserRepository repository, JwtTokenService jwtService, SessionStore sessionStore) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.sessionStore = sessionStore;
    }



    private UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName());
    }

    @PostMapping("/")
    @Operation(summary = "Create a new user")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserInput req) {
        UUID id = UUID.randomUUID();
        String hashed = hasher.hash(req.getPassword());
        User user = new User(id, req.getEmail(), hashed, req.getName());
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(user)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserInput req) {
        User existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setEmail(req.getEmail());
        existing.setHashedPassword(req.getHashedPassword());
        existing.setName(req.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(existing)));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an existing user")
    public ResponseEntity<String> patchUser(@PathVariable UUID id, @Valid @RequestBody PatchUserInput req) {
        User existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (req.getEmail() != null) {
            existing.setEmail(req.getEmail());
        }
        if (req.getHashedPassword() != null) {
            existing.setHashedPassword(req.getHashedPassword());
        }
        if (req.getName() != null) {
            existing.setName(req.getName());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(existing)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by its ID")
    public ResponseEntity<String> getUser(@PathVariable UUID id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(user)));
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user from JWT token")
    public ResponseEntity<String> getMe(@org.springframework.web.bind.annotation.RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);
        try {
            var data = jwtService.verify(token);
            User user = repository.findById(data.getId()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(gson.toJson(toDto(user)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List users with optional query and pagination")
    public String listUsers(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int page) {
        int offset = (page - 1) * pageSize;
        Iterable<User> found = repository.search(q, email, pageSize, offset);
        List<UserDTO> list = new ArrayList<>();
        found.forEach(u -> list.add(toDto(u)));
        return gson.toJson(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by its ID")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (repository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
