package com.oopecommerce.models.users;

import java.util.UUID;

public class User {
    final UUID id;
    String email;
    String hashedPassword;
    String name;

    public User(UUID id, String email, String hashedPassword, String name) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    public Boolean equals(User u) {
        return this.id.equals(u.getId());
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
