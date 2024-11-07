package com.example.demo.service;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartRequest;
import com.example.demo.domain.cart.CartResponse;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductResponse;
import com.example.demo.infra.excepitions.ValidaRegraDeNegocios;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;


    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;

    }



    public CartResponse getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("Cart not found"));

        return new CartResponse(cart.getId(),cart.getItems());
    }

    public List<CartResponse> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> new CartResponse(cart.getId(), cart.getItems()))
                .collect(Collectors.toList());
    }







}
