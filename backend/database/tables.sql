SET SEARCH_PATH TO ecommerce_schema;

DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
	product_id SERIAL PRIMARY KEY,
	name varchar(100),
	price int
);

CREATE TABLE IF NOT EXISTS account (
	account_id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULl,
	username varchar(100) UNIQUE NOT NULL,
	password varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_item (
	cart_item_id SERIAL PRIMARY KEY,
	account_id int,
	product_id int,
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(product_id),
	CONSTRAINT account_fk FOREIGN KEY(account_id) REFERENCES account(account_id)
);