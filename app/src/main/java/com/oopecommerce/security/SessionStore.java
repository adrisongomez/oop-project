package com.oopecommerce.security;

import com.oopecommerce.models.users.User;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionStore {
    void record(User user, HttpServletRequest request);
}
