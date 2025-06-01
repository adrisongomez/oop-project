package com.graphlog.notifications.models;

import java.util.Date;
import java.util.UUID;

import com.graphlog.auth.models.User;

public abstract class NotificationRequest {
    UUID id;
    Message message;
    NotificationType type;
    User sender;
    Integer retryCount;

    Date scheduledFor;
    Date lastRetry;

	public UUID getId() {
		return id;
	}

	public Message getMessage() {
		return message;
	}

	public NotificationType getType() {
		return type;
	}

	public User getSender() {
		return sender;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public Date getScheduledFor() {
		return scheduledFor;
	}

	public Date getLastRetry() {
		return lastRetry;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public void setScheduledFor(Date scheduledFor) {
		this.scheduledFor = scheduledFor;
	}

	public void setLastRetry(Date lastRetry) {
		this.lastRetry = lastRetry;
	}
}
