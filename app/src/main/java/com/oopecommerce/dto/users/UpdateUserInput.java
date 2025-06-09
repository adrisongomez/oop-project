package com.oopecommerce.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Payload for updating a user.
 */
@Schema(description = "Payload for updating an existing user")
public class UpdateUserInput {
    @Schema(description = "Updated email address", required = true)
    @NotBlank
    @Email
    private String email;
    @Schema(description = "Updated password hash", required = true)
    @NotBlank
    private String hashedPassword;
    @Schema(description = "Updated display name", required = true)
    @NotBlank
    private String name;

    public UpdateUserInput() {}

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
