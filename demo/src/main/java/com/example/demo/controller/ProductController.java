package com.example.demo.controller;

import com.example.demo.domain.product.ProductDeleteRequest;
import com.example.demo.domain.product.ProductRequest;
import com.example.demo.domain.product.ProductResponse;
import com.example.demo.domain.product.ProductUpdateRequest;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class    ProductController {
    @Autowired
    private  ProductService productService;

    @Transactional
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/categorias/{category}")
    public ResponseEntity<List<ProductResponse>> getProductByCategory(@PathVariable String category ) {
        return ResponseEntity.ok(productService.getProductByCategory(category));
    }


    @GetMapping
    public ResponseEntity<Page<ProductResponse>> listProducts(Pageable pageable) {
        Page<ProductResponse> page = productService.listProducts(pageable);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<ProductResponse> updateProduct( @RequestBody @Valid ProductUpdateRequest productRequest) {
        ProductResponse updatedProduct = productService.updateProduct( productRequest);
        return ResponseEntity.ok(updatedProduct);
    }
    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@RequestBody @Valid ProductDeleteRequest productDeleteRequest) {
        productService.deleteProduct(productDeleteRequest);
        return ResponseEntity.noContent().build();
    }
}
