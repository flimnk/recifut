package com.example.demo.controller;

import com.example.demo.domain.PointExchange.PointExchangeDelete;
import com.example.demo.domain.PointExchange.PointExchangeRequest;
import com.example.demo.domain.PointExchange.PointExchangeResponse;
import com.example.demo.domain.PointExchange.PointExchangeUpdateRequest;
import com.example.demo.service.PointExchangeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/point-exchange")
public class PointExchangeController {

    @Autowired
    private  PointExchangeService pointExchangeService;


    @Transactional
    @PostMapping
    public ResponseEntity<PointExchangeResponse> exchangePoints(@RequestBody @Valid PointExchangeRequest request) {
        return ResponseEntity.ok(pointExchangeService.exchangePoints(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointExchangeResponse> getExchangeById(@PathVariable Long id) {
        return ResponseEntity.ok(pointExchangeService.getExchangeById(id));
    }
    @GetMapping
    public ResponseEntity<Page<PointExchangeResponse>> listPointExchanges(Pageable pageable) {
        Page<PointExchangeResponse> page = pointExchangeService.listPointExchanges(pageable);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<PointExchangeResponse> updatePointExchange( @RequestBody @Valid PointExchangeUpdateRequest request) {
        return ResponseEntity.ok(pointExchangeService.updatePointExchange(request));
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointExchange(@RequestBody @Valid PointExchangeDelete request) {
        pointExchangeService.deletePointExchange(request);
        return ResponseEntity.noContent().build();
    }
}
