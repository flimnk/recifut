package com.example.demo.domain.PointExchange;

import jakarta.validation.constraints.NotNull;

public record PointExchangeDelete(
        @NotNull
        Long id
) {
}
