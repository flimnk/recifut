package com.example.demo.service;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartResponse;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductResponse;
import com.example.demo.domain.user.*;
import com.example.demo.infra.excepitions.ValidaRegraDeNegocios;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public UserResponse createUser(UserRequest request) {
        validateUserRequest(request);

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
        user.setPoints(request.points());

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        userRepository.save(user);


        List<CartItem> cartItems = new ArrayList<>();


        CartResponse cartResponse = new CartResponse(cart.getId(), cartItems);

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPoints(), cartResponse);
    }

    public UserResponse getUserByName(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ValidaRegraDeNegocios("User not found"));


    Cart cart = user.getCart();


        CartResponse cartResponse = new CartResponse(cart.getId(), cart.getItems());

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPoints(), cartResponse);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);


                    assert cart != null;
                    CartResponse cartResponse = new CartResponse(cart.getId(),cart.getItems());

                    return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPoints(), cartResponse);
                })
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(UserUpdateRequest updateRequest) {
        User existingUser = userRepository.findByEmail(updateRequest.email())
                .orElseThrow(() -> new ValidaRegraDeNegocios("User " + updateRequest.email() + " not found"));

        applyUpdatesToUser(existingUser, updateRequest);

        userRepository.save(existingUser);

        Cart cart = cartRepository.findByUserId(existingUser.getId())
                .orElseThrow(() -> new ValidaRegraDeNegocios("Cart not found for user"));



        CartResponse cartResponse = new CartResponse(cart.getId(), cart.getItems());

        return new UserResponse(existingUser.getId(), existingUser.getUsername(), existingUser.getEmail(), existingUser.getPoints(), cartResponse);
    }




    private void applyUpdatesToUser(User user, UserUpdateRequest updateRequest) {
        if (updateRequest.username() != null) user.setUsername(updateRequest.username());
        if (updateRequest.password() != null) user.setPassword(updateRequest.password());
        if (updateRequest.email() != null) user.setEmail(updateRequest.email());
    }

    public void validateUserRequest(UserRequest userRequest) {
        userRepository.findByUsername(userRequest.username())
                .ifPresent(user -> {
                    throw new ValidaRegraDeNegocios("Username already exists");
                });

        userRepository.findByEmail(userRequest.email())
                .ifPresent(user -> {
                    throw new ValidaRegraDeNegocios("Email already exists");
                });
    }

    public CartResponse addProductToUserCart(Long userId, Long productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("Product not found"));
        Cart cart = user.getCart();
        System.out.println(user);

        System.out.println(cart);

        System.out.println("dsuhcfewfdf");

        CartItem existingCartItem = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } else {
            System.out.println("existe não");
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(1);
            cart.getItems().add(newCartItem);
        }

        cartRepository.save(cart);

        return new CartResponse(cart.getId(),cart.getItems());
    }

    public CartResponse removeProductFromUserCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("Product not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new ValidaRegraDeNegocios("Cart not found for this user");
        }

        // Verifica se o produto está no carrinho
        CartItem existingCartItem = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {

            if (existingCartItem.getQuantity() > 1) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
            } else {
                cart.getItems().remove(existingCartItem);
            }
        } else {
            throw new ValidaRegraDeNegocios("Product not found in user's cart");
        }

        cartRepository.save(cart);

        return new CartResponse(cart.getId(),cart.getItems());
    }

    public CartResponse excluiProductFromUserCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("Product not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new ValidaRegraDeNegocios("Cart not found for this user");
        }

        // Verifica se o produto está no carrinho
        CartItem existingCartItem = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
                cart.getItems().remove(existingCartItem);
                existingCartItem.setQuantity(0);

        } else {
            throw new ValidaRegraDeNegocios("Product not found in user's cart");
        }

        cartRepository.save(cart);

        return new CartResponse(cart.getId(),cart.getItems());
    }


    public CartResponse excluiAllProductFromUserCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidaRegraDeNegocios("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new ValidaRegraDeNegocios("Cart not found for this user");
        }
        if (cart.getItems().isEmpty()) {
            throw new ValidaRegraDeNegocios("Cart is empty");
        }

        // Limpar os itens do carrinho
        cart.getItems().clear(); // Remove todos os itens do carrinho

        // Salvar o carrinho atualizado
        cartRepository.save(cart);

        return new CartResponse(cart.getId(), cart.getItems());
    }



}
