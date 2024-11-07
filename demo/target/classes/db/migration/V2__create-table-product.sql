CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    points INT NOT NULL,
    category VARCHAR(255),
    clube VARCHAR(255),
    description TEXT,
    image1Url VARCHAR(255),
    image2Url VARCHAR(255),
    image3Url VARCHAR(255),
    oldPrice DECIMAL(10, 2), -- Preço original do produto
    newPrice DECIMAL(10, 2), -- Preço promocional do produto
    ratings DECIMAL(2, 1), -- Média de avaliações (ex: 4.5)
    totalRatings INT DEFAULT 0, -- Número total de avaliações
    brand VARCHAR(255), -- Marca do produto
    sizes VARCHAR(255), -- Tamanhos disponíveis (pode ser uma lista separada por vírgulas)
    PRIMARY KEY (id)
);
