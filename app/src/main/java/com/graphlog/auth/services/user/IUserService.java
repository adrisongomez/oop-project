package com.graphlog.auth.services.user;

import java.util.UUID;

import com.graphlog.auth.models.User;

public interface IUserService {
    public User save(User user);
    public User delete(User user);
    public User findById(UUID id);
    public User findByEmail(String email);
}
