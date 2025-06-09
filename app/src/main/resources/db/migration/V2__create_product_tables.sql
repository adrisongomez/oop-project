CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS simple_product (
    id UUID PRIMARY KEY,
    CONSTRAINT fk_simple_product_product FOREIGN KEY (id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_media (
    id UUID PRIMARY KEY,
    alt VARCHAR(255),
    url VARCHAR(255),
    type VARCHAR(50),
    position INT,
    product_id UUID,
    CONSTRAINT fk_product_media_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_variants (
    id UUID PRIMARY KEY,
    sku VARCHAR(255),
    position INT,
    feature_media_id UUID,
    price DOUBLE PRECISION,
    weight_in_grams VARCHAR(255),
    product_id UUID,
    CONSTRAINT fk_product_variants_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_product_variants_media FOREIGN KEY (feature_media_id) REFERENCES product_media(id)
);

CREATE TABLE IF NOT EXISTS product_variant_options (
    product_variant_id UUID NOT NULL,
    option_value VARCHAR(255),
    CONSTRAINT fk_product_variant_options_variant FOREIGN KEY (product_variant_id) REFERENCES product_variants(id) ON DELETE CASCADE
);

