package com.example.demo.domain.PointExchange;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record PointExchangeUpdateRequest(
        @NotNull
        Long id,
        Long userId,
        Long productId,
        @Positive(message = "Quantity must be positive") Integer quantity,
        LocalDateTime date
) {
}
