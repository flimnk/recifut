CREATE TABLE cart (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNIQUE,  -- Relação 1:1 com usuário
    total_points INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_cart FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
