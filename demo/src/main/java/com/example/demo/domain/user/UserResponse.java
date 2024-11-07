package com.example.demo.domain.user;


import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartResponse;

public record UserResponse(
        Long id,
        String username,
        String email,
        int points,
        CartResponse cart
) {
    public UserResponse(Long id, String username, String email, int points, CartResponse cart) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.points = points;
        this.cart = cart;
    }
}
