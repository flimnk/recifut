package com.example.demo.domain.cart;

import com.example.demo.domain.product.ProductRequest;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

public record CartRequest(
        @NotNull(message = "User ID must not be null")
        Long userId,

        @NotNull(message = "Products must not be null")
        List<String> productsNames
) {}