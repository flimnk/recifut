package com.example.demo.domain.cart;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    private int totalPoints;

    // Cart.java
    @Override
    public String toString() {
        return "Cart{id=" + id + ", totalPoints=" + totalPoints + ", itemsCount=" + (items != null ? items.size() : 0) + "}";
    }

}
