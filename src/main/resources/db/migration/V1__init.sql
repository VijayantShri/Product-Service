CREATE TABLE product
(
    id            BIGINT NOT NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    is_public     BIT(1) NULL,
    price DOUBLE NULL,
    quantity      INT NULL,
    title         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_category_seq
(
    next_val BIGINT NULL
);

CREATE TABLE product_seq
(
    next_val BIGINT NULL
);

ALTER TABLE product
    ADD CONSTRAINT FK5cypb0k23bovo3rn1a5jqs6j4 FOREIGN KEY (category_id) REFERENCES product_category (id) ON DELETE NO ACTION;

CREATE INDEX FK5cypb0k23bovo3rn1a5jqs6j4 ON product (category_id);