package com.graphlog.auth.services.session;

import java.util.UUID;

import com.graphlog.auth.models.Session;

public interface ISessionService {
    public Session save(Session session);
    public Session delete(Session session);
    public Session findById(UUID id);
    public Session findByEmail(String email);
}
