package com.example.demo.domain.PointExchange;

    import java.time.LocalDateTime;

    public record PointExchangeResponse(
            Long id,
            Long userId,
            Long productId,
            Integer quantity,
            LocalDateTime date
    ) {

        public  PointExchangeResponse(PointExchange pointExchange){
            this(pointExchange.getId(), pointExchange.getUser().getId(), pointExchange.getProduct().getId(), pointExchange.getQuantity(),pointExchange.getDate());
        }
    }
