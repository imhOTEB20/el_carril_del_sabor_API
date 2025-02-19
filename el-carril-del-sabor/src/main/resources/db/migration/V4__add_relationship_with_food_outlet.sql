ALTER TABLE orders
ADD COLUMN food_outlet BIGINT NOT NULL,
ADD CONSTRAINT fk_order_food_outlet
FOREIGN KEY (food_outlet) REFERENCES food_outlets(id_food_outlet);
