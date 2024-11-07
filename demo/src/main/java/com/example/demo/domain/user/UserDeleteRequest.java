package com.example.demo.domain.user;

import jakarta.validation.constraints.NotBlank;

import javax.swing.text.Style;

public record UserDeleteRequest(
        @NotBlank
        String email
) {

}
