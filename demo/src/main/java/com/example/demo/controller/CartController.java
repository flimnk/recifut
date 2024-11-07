package com.example.demo.controller;

import com.example.demo.domain.cart.CartRequest;
import com.example.demo.domain.cart.CartResponse;
import com.example.demo.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    // Obter carrinho por ID
    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long cartId) {
        CartResponse cartResponse = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartResponse);
    }

    // Obter todos os carrinhos
    @GetMapping("/all")
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        List<CartResponse> cartResponses = cartService.getAllCarts();
        return ResponseEntity.ok(cartResponses);
    }

}
