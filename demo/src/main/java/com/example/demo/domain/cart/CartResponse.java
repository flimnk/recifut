package com.example.demo.domain.cart;



import com.example.demo.domain.CartItem;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductResponse;

import java.util.List;

public record CartResponse(
        Long id,
        List<CartItem> items
) {
}
