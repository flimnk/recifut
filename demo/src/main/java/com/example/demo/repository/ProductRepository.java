package com.example.demo.repository;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductDeleteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);


    Page<Product> findAll(Pageable pageable);

    boolean existsByName(String name);

    void deleteByName(Product product);

    List<Product> findAllByCategory(String category);
}
