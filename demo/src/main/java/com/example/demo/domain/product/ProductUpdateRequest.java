package com.example.demo.domain.product;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;

public record ProductUpdateRequest(
        @NotBlank
        String name,

        @PositiveOrZero(message = "Points must be zero or positive")
        Integer points,

        String category,
        String clube,
        String description,
        String promocao,
        Double oldPrice,
        Double newPrice,
        Integer estrelas,
        String marca,
        String[] sizes,
        Integer totalRatings
) {}
