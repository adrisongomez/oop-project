-- Insert initial seed data for local development

INSERT INTO users (id, email, hashed_password, name) VALUES
  ('11111111-1111-1111-1111-111111111111', 'demo@local.test', '$2a$10$Z6L6DAoWbDvVifS414PcgOffQ4Ko4x65L70QSY2BJ9Tc.ff3JBmqO', 'Demo User');

INSERT INTO products (id, name, description, status) VALUES
  ('22222222-2222-2222-2222-222222222222', 'Producto Demo', 'Articulo de ejemplo', 'ACTIVE');

INSERT INTO simple_product (id) VALUES
  ('22222222-2222-2222-2222-222222222222');

INSERT INTO product_media (id, alt, url, type, position, product_id) VALUES
  ('22222222-2222-2222-2222-222222222223', 'Imagen Demo', 'https://example.com/image.png', 'IMAGE', 1, '22222222-2222-2222-2222-222222222222');

INSERT INTO product_variants (id, sku, position, feature_media_id, price, weight_in_grams, product_id) VALUES
  ('22222222-2222-2222-2222-222222222224', 'SKU-DEMO', 1, '22222222-2222-2222-2222-222222222223', 19.99, '200', '22222222-2222-2222-2222-222222222222');

INSERT INTO product_variant_options (product_variant_id, option_value) VALUES
  ('22222222-2222-2222-2222-222222222224', 'Talla Unica');

INSERT INTO inventory_locations (id, warehouse_code, street_address, city, country) VALUES
  ('33333333-3333-3333-3333-333333333333', 'WH-LOCAL', 'Warehouse 1', 'Springfield', 'USA');

INSERT INTO inventory_levels (id, product_id, location_id, quantity) VALUES
  ('33333333-3333-3333-3333-333333333334', '22222222-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333333', 5);

INSERT INTO carts (id, user_id, created_at, updated_at) VALUES
  ('11111111-1111-1111-1111-111111111113', '11111111-1111-1111-1111-111111111111', NOW(), NOW());

INSERT INTO cart_line_items (id, cart_id, variant_id, quantity, created_at, updated_at) VALUES
  ('11111111-1111-1111-1111-111111111114', '11111111-1111-1111-1111-111111111113', '22222222-2222-2222-2222-222222222224', 1, NOW(), NOW());

INSERT INTO payments (id, user_id, amount, status, method, created_at) VALUES
  ('44444444-4444-4444-4444-444444444444', '11111111-1111-1111-1111-111111111111', 19.99, 'SUCCESS', 'CARD', NOW());
