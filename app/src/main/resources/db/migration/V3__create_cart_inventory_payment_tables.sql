CREATE TABLE IF NOT EXISTS shipping_addresses (
    id UUID PRIMARY KEY,
    street_address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    zip_code VARCHAR(20),
    country VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS carts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_carts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cart_line_items (
    id UUID PRIMARY KEY,
    cart_id UUID NOT NULL,
    variant_id UUID NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_line_item_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT fk_line_item_variant FOREIGN KEY (variant_id) REFERENCES product_variants(id)
);

CREATE TABLE IF NOT EXISTS inventory_locations (
    id UUID PRIMARY KEY,
    warehouse_code VARCHAR(100) NOT NULL,
    street_address VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    method VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_payments_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

