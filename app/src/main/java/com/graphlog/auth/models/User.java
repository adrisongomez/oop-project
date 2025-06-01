package com.graphlog.auth.models;

import java.util.Date;

public class User{
    public enum UserStatus {
        ACTIVE,
        INACTIVE,
        BLOCKED,
        PENDING_ACTIVATION,
    }

    final String id;
    final String firstName;
    final String lastName;
    final String email;
    Role role;
    String hashedPassword;
    UserStatus status;
    Date lastLoginAttempt;
    Date updatedAt;
    Date deletedAt;
    User createdBy;
    
    User(String id, String firstName, String lastName, String email, String hashedPassword, Role role, Date lastLoginAttempt, Date updatedAt, Date deletedAt, User createdBy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.lastLoginAttempt = lastLoginAttempt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.role = role;
    }

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public UserStatus getStatus() {
		return status;
	}

	public Date getLastLoginAttempt() {
		return lastLoginAttempt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}
}

