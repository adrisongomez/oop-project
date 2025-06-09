package com.oopecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import com.oopecommerce.models.users.User;

public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    void save(User user);
    void delete(UUID id);
    Iterable<User> findAll();
    Iterable<User> search(String query, String email, int limit, int offset);
}
