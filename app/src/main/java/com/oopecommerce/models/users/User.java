package com.oopecommerce.models.users;

import java.util.UUID;
import java.util.regex.Pattern;

public class User {
    private final UUID id;
    private String email;
    private String hashedPassword;
    private String name;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public User(UUID id, String email, String hashedPassword, String name) {
        this.id = id;
        this.setEmail(email);
        this.hashedPassword = hashedPassword;
        this.setName(name);
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
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getDashboardInfo() {
        return "Welcome, " + this.name + "!";
    }
}
