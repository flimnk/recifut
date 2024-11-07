package com.example.demo.controller;

import com.example.demo.domain.cart.CartResponse;
import com.example.demo.domain.user.UserDeleteRequest;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.domain.user.UserResponse;
import com.example.demo.domain.user.UserUpdateRequest;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;


    @Transactional
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String name) {
        System.out.println(name);
        return ResponseEntity.ok(userService.getUserByName(name));
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @Transactional
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest updateRequest) {
        UserResponse updatedUser = userService.updateUser(updateRequest);
        return ResponseEntity.ok(updatedUser);
    }
//    @Transactional
//    @DeleteMapping
//    public ResponseEntity<Void> deleteUser(@RequestBody @Valid UserDeleteRequest userDeleteRequest) {
//        userService.deleteUser(userDeleteRequest);
//        return ResponseEntity.noContent().build();
//    }
    @PostMapping("/{userId}/cart/addProduct/{produtctId}")
    public ResponseEntity<CartResponse> addProductToUserCart(@PathVariable Long userId, @PathVariable Long produtctId) {
        CartResponse cartResponse = userService.addProductToUserCart(userId, produtctId);
        return ResponseEntity.ok(cartResponse);
    }

    // Remover produto do carrinho do usuário
    @DeleteMapping("/{userId}/cart/removeProduct/{productId}")
    public ResponseEntity<CartResponse> removeProductFromUserCart(@PathVariable Long userId, @PathVariable Long productId) {
        CartResponse cartResponse = userService.removeProductFromUserCart(userId, productId);
        return ResponseEntity.ok(cartResponse);
    }
    @DeleteMapping("/{userId}/cart/excluirProduct/{productId}")
    public ResponseEntity<CartResponse> excluirProductFromUserCart(@PathVariable Long userId, @PathVariable Long productId) {
        CartResponse cartResponse = userService.excluiProductFromUserCart(userId, productId);
        return ResponseEntity.ok(cartResponse);
    }
    @DeleteMapping("/{userId}/cart/excluirAllProducts")
    public ResponseEntity<CartResponse> excluirAllProductFromUserCart(@PathVariable Long userId) {
        CartResponse cartResponse = userService.excluiAllProductFromUserCart(userId);
        return ResponseEntity.ok(cartResponse);
    } 


}
