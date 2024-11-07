package com.example.demo.service;

import com.example.demo.domain.PointExchange.*;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.user.User;
import com.example.demo.infra.excepitions.ValidaRegraDeNegocios;
import com.example.demo.repository.PointExchangeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class PointExchangeService {

    private final PointExchangeRepository pointExchangeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PointExchangeService(PointExchangeRepository pointExchangeRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.pointExchangeRepository = pointExchangeRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public PointExchangeResponse exchangePoints(PointExchangeRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new RuntimeException("Product not found"));

        if (user.getPoints() < product.getPoints() * request.quantity()) {
            throw new RuntimeException("Insufficient points");
        }

        user.setPoints(user.getPoints() - product.getPoints() * request.quantity());
        PointExchange pointExchange = new PointExchange();
        pointExchange.setUser(user);
        pointExchange.setProduct(product);
        pointExchange.setQuantity(request.quantity());
        pointExchange.setDate(LocalDateTime.now());

        pointExchangeRepository.save(pointExchange);
        userRepository.save(user);

        return new PointExchangeResponse(pointExchange.getId(), user.getId(), product.getId(), pointExchange.getQuantity(), pointExchange.getDate());
    }

    public PointExchangeResponse getExchangeById(Long id) {
        PointExchange pointExchange = pointExchangeRepository.findById(id).orElseThrow(() -> new ValidaRegraDeNegocios("Point exchange not found"));
        return new PointExchangeResponse(pointExchange.getId(), pointExchange.getUser().getId(), pointExchange.getProduct().getId(), pointExchange.getQuantity(), pointExchange.getDate());
    }

    public Page<PointExchangeResponse> listPointExchanges(Pageable pageable) {
        return pointExchangeRepository.findAll(pageable).map(PointExchangeResponse::new);
    }
    public PointExchangeResponse updatePointExchange(PointExchangeUpdateRequest updateRequest) {
        PointExchange existingExchange = pointExchangeRepository.findById(updateRequest.id())
                .orElseThrow(() -> new ValidaRegraDeNegocios("Point exchange with ID " + updateRequest.id() + " not found"));

        // Atualizar quantidade do produto e saldo do usuário
        int oldQuantity = existingExchange.getQuantity();
        int newQuantity = updateRequest.quantity() != null ? updateRequest.quantity() : 0;

        if (existingExchange.getUser() != null && existingExchange.getProduct() != null) {
            int pointsDifference = (oldQuantity - newQuantity) * existingExchange.getProduct().getPoints();
            existingExchange.getUser().setPoints(existingExchange.getUser().getPoints() + pointsDifference);
        }

        applyUpdatesToPointExchange(existingExchange, updateRequest);

        pointExchangeRepository.save(existingExchange);
        if (existingExchange.getUser() != null) {
            userRepository.save(existingExchange.getUser());
        }

        return new PointExchangeResponse(
                existingExchange.getId(),
                existingExchange.getUser().getId(),
                existingExchange.getProduct().getId(),
                existingExchange.getQuantity(),
                existingExchange.getDate()
        );
    }

    // Função auxiliar para aplicar atualizações
    private void applyUpdatesToPointExchange(PointExchange pointExchange, PointExchangeUpdateRequest updateRequest) {
        if (updateRequest.quantity() != null) pointExchange.setQuantity(updateRequest.quantity());
        if (updateRequest.date() != null) pointExchange.setDate(updateRequest.date());
    }




    // Função auxiliar para aplicar atualizações


    public void deletePointExchange(PointExchangeDelete pointExchangeDelete) {
        PointExchange existingExchange = pointExchangeRepository.findById(pointExchangeDelete.id())
                .orElseThrow(() -> new ValidaRegraDeNegocios("Point exchange not found"));

        pointExchangeRepository.delete(existingExchange);
    }




}
