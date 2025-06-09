package com.oopecommerce.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

/**
 * Payload for partially updating a user. All fields are optional.
 */
@Schema(description = "Payload for partially updating a user")
public class PatchUserInput {
    @Schema(description = "Updated email address")
    @Email
    private String email;

    @Schema(description = "Updated password hash")
    private String hashedPassword;

    @Schema(description = "Updated display name")
    private String name;

    public PatchUserInput() {}

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
