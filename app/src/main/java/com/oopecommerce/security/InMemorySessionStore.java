package com.oopecommerce.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oopecommerce.models.users.User;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class InMemorySessionStore implements SessionStore {
    private final List<SessionData> sessions = new ArrayList<>();

    @Override
    public void record(User user, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        sessions.add(new SessionData(user.getId(), ip, Instant.now()));
    }

    public List<SessionData> getSessions() {
        return sessions;
    }
}
