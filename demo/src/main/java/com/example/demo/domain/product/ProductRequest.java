package com.example.demo.domain.product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;

public record ProductRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @PositiveOrZero(message = "Points must be zero or positive")
        int points,

        @NotBlank(message = "Category must not be blank")
        String category,

        @NotBlank(message = "Clube must not be blank")
        String clube,

        @NotBlank
        String description,
        @NotBlank
        String image1Url,
        @NotBlank
        String image2Url,
        @NotBlank
        String image3Url,

        // Novos campos
        @NotBlank
        String promocao,
        @NotNull
        double oldPrice,
        @NotNull
        double newPrice,
        @NotNull
        int estrelas,
        @NotBlank
        String marca,
        @NotEmpty
        String[] sizes,
        @NotNull
        int totalRatings
) {
        @Override
        public String toString() {
                return "ProductRequest{" +
                        "name='" + name + '\'' +
                        ", points=" + points +
                        ", category='" + category + '\'' +
                        ", clube='" + clube + '\'' +
                        ", description='" + description + '\'' +
                        ", image1Url='" + image1Url + '\'' +
                        ", image2Url='" + image2Url + '\'' +
                        ", image3Url='" + image3Url + '\'' +
                        ", promocao='" + promocao + '\'' +
                        ", oldPrice=" + oldPrice +
                        ", newPrice=" + newPrice +
                        ", estrelas=" + estrelas +
                        ", marca='" + marca + '\'' +
                        ", sizes=" + Arrays.toString(sizes) +
                        ", totalRatings=" + totalRatings +
                        '}';
        }
}
