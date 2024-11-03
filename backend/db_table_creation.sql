SET SEARCH_PATH TO ecommerce_schema;

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
	product_id SERIAL PRIMARY KEY,
	name varchar(100),
	price int
);

CREATE TABLE IF NOT EXISTS customer (
	customer_id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULl,
	email varchar(100) UNIQUE NOT NULL,
	password varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart (
	cart_id SERIAL PRIMARY KEY,
	customer_id int,
	product_id int,
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(product_id),
	CONSTRAINT customer_fk FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);