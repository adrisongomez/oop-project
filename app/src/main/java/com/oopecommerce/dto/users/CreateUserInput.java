package com.oopecommerce.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Payload for creating a user.
 */
@Schema(description = "Payload for creating a new user")
public class CreateUserInput {
    @Schema(description = "Email address of the user", required = true)
    @NotBlank
    @Email
    private String email;
    @Schema(description = "Plain password that will be hashed on the server", required = true)
    @NotBlank
    private String password;
    @Schema(description = "Display name", required = true)
    @NotBlank
    private String name;

    public CreateUserInput() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
