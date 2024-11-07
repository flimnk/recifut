package com.example.demo.domain.PointExchange;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PointExchangeRequest(
        @NotNull(message = "User ID must not be null")
        Long userId,

        @NotNull(message = "Product ID must not be null")
        Long productId,

        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity
) {}
