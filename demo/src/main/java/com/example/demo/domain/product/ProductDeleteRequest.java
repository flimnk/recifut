package com.example.demo.domain.product;

import jakarta.validation.constraints.NotBlank;

public record ProductDeleteRequest(
        @NotBlank
        String name
) {}
