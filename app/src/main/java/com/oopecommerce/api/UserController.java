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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.oopecommerce.dto.users.UserDTO;
import com.oopecommerce.dto.users.CreateUserInput;
import com.oopecommerce.dto.users.UpdateUserInput;
import com.oopecommerce.dto.users.PatchUserInput;
import com.oopecommerce.models.users.User;
import com.oopecommerce.utils.hasher.Hasher;
import com.oopecommerce.utils.hasher.BcryptHasher;
import com.oopecommerce.repositories.IUserRepository;
import com.oopecommerce.security.JwtTokenService;
import com.oopecommerce.security.SessionStore;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations related to application users")
public class UserController {

    private final Hasher hasher = new BcryptHasher();
    private final IUserRepository repository;
    private final JwtTokenService jwtService;

    public UserController(IUserRepository repository, JwtTokenService jwtService, SessionStore sessionStore) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    private UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserInput req) {
        UUID id = UUID.randomUUID();
        String hashed = hasher.hash(req.getPassword());
        User user = new User(id, req.getEmail(), hashed, req.getName());
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDto(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an existing user")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserInput req) {
        User existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setEmail(req.getEmail());
        existing.setHashedPassword(req.getHashedPassword());
        existing.setName(req.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDto(existing));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Partially update an existing user")
    public ResponseEntity<UserDTO> patchUser(@PathVariable UUID id, @Valid @RequestBody PatchUserInput req) {
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
                .body(toDto(existing));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a user by its ID")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDto(user));
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current user from JWT token")
    public ResponseEntity<UserDTO> getMe(
            @org.springframework.web.bind.annotation.RequestHeader("Authorization") String authHeader) {
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
                    .body(toDto(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List users with optional query and pagination")
    public ResponseEntity<List<UserDTO>> listUsers(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int page) {
        int offset = (page - 1) * pageSize;
        Iterable<User> found = repository.search(q, pageSize, offset);
        List<UserDTO> list = new ArrayList<>();
        found.forEach(u -> list.add(toDto(u)));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a user by its ID")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (repository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
