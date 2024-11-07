package com.example.demo.domain.user;

import com.example.demo.domain.PointExchange.PointExchange;
import com.example.demo.domain.cart.Cart;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private int points;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointExchange> pointExchanges;

    // User.java
    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "', points=" + points + "}";
    }

}
