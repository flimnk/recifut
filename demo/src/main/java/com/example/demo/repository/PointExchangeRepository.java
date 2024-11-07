package com.example.demo.repository;

import com.example.demo.domain.PointExchange.PointExchange;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointExchangeRepository extends JpaRepository<PointExchange, Long> {

    List<PointExchange> findByUser(User user);
    Page<PointExchange> findAll(Pageable pageable);

    List<PointExchange> findByProduct(Product product);
}
