package com.example.demo.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(String password,
                                String username,
                                     @NotBlank
                                     String email
                                ) {
}
