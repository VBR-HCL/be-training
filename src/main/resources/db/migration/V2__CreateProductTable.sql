CREATE TABLE poc_product
(
    id       BIGINT IDENTITY PRIMARY KEY,
    name     VARCHAR(255),
    group_id BIGINT NOT NULL
);

ALTER TABLE poc_product
    ADD CONSTRAINT FK_product_group
        FOREIGN KEY (group_id) REFERENCES poc_group (id);