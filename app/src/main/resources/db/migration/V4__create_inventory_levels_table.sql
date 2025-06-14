CREATE TABLE IF NOT EXISTS inventory_levels (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    location_id UUID NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_inventory_levels_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_inventory_levels_location FOREIGN KEY (location_id) REFERENCES inventory_locations(id) ON DELETE CASCADE,
    CONSTRAINT uq_inventory_levels_product_location UNIQUE (product_id, location_id)
);
