    package com.example.demo.domain.product;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Arrays;


    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = " product")
    @Getter
    @Setter
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private int points;
        private String category;
        private String clube; // clube
        private String description;
        private String image1Url;
        private String image2Url;
        private String image3Url;
        private String promocao;
        private double oldPrice;
        private double newPrice;
        private int estrelas;
        private String marca;

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
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

        private String[] sizes;
        private int totalRatings;

    }
