package com.graphlog.auth.models;

import java.util.Date;
import java.util.UUID;

public class UserInvitation {
    public enum InvitationStatus {
        PENDING,
        ACCEPTED,
        EXPIRED,
        CANCELLED,
    }

    UUID id;
    String invitedEmail;
    User requestedBy;
    Date invitedAt;
    Role role;
    InvitationStatus status;
    Integer expireInMs;
    Date acceptedAt;
    String otp;

    public Boolean isExpired() {
        return false;
    }

    public void accept() {}
    public void cancel() {}
}
