package com.example.demo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username must not be blank")
        String username,

        @NotBlank(message = "Password must not be blank")
        String password,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email must not be blank")
        String email,

        @Min(value = 0, message = "Points must be at least 0")
        int points
) {}
