package com.example.demo.domain.product;

public record ProductResponse(
        Long id,
        String name,
        int points,
        String category,
        String clube,
        String description,
        String image1Url,
        String image2Url,
        String image3Url,


        // Novos campos
        String promocao,
        double oldPrice,
        double newPrice,
        int estrelas,
        String marca,
        String[] sizes,
        int totalRatings
) {
        public ProductResponse(Product product) {
                this(
                        product.getId(),
                        product.getName(),
                        product.getPoints(),
                        product.getCategory(),
                        product.getClube(),
                        product.getDescription(),
                        product.getImage1Url(),
                        product.getImage2Url(),
                        product.getImage3Url(),
                        product.getPromocao(),
                        product.getOldPrice(),
                        product.getNewPrice(),
                        product.getEstrelas(),
                        product.getMarca(),
                        product.getSizes(),
                        product.getTotalRatings()
                );
        }
}
