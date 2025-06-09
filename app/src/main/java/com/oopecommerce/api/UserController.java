package com.oopecommerce.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations related to application users")
public class UserController {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();
    private final Gson gson = new Gson();
    private final Hasher hasher = new BcryptHasher();

    public UserController() {
        // Mock data
        User u1 = new User(UUID.fromString("00000000-0000-0000-0000-000000000001"), "john@example.com", "hash1", "John");
        User u2 = new User(UUID.fromString("00000000-0000-0000-0000-000000000002"), "jane@example.com", "hash2", "Jane");
        users.put(u1.getId(), u1);
        users.put(u2.getId(), u2);
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
        users.put(id, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(user)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserInput req) {
        User existing = users.get(id);
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
        User existing = users.get(id);
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
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(toDto(user)));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List users with optional query and pagination")
    public String listUsers(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int page) {
        List<User> source = new ArrayList<>(users.values());
        List<UserDTO> list = source.stream()
                .filter(u -> email == null || u.getEmail().contains(email))
                .filter(u -> q == null || u.getName().contains(q) || u.getEmail().contains(q))
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .map(this::toDto)
                .collect(Collectors.toList());
        return gson.toJson(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by its ID")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        User removed = users.remove(id);
        if (removed == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
