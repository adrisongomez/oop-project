package com.oopecommerce.dto.users;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO used to expose user information without the hashed password.
 */
@Schema(description = "User details without sensitive information")
public class UserDTO {
    @Schema(description = "Unique identifier of the user")
    private UUID id;

    @Schema(description = "User email address")
    private String email;

    @Schema(description = "User display name")
    private String name;

    public UserDTO() {}

    public UserDTO(UUID id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
