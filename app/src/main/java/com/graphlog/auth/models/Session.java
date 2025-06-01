package com.graphlog.auth.models;

import java.util.Date;
import java.util.UUID;

public class Session {
    UUID id;
    User user;
    String token;
    Date lastActivity;
    String deviceInfo;
    String ipAddress;
    Boolean isActive;
    Integer expireInMs;

    public Boolean isExpired() {
        return false;
    }

    public void refresh() {
    }
}

