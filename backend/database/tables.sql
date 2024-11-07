SET SEARCH_PATH TO ecommerce_schema;

DROP TABLE IF EXISTS product_to_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS category (
	category_id SERIAL PRIMARY KEY,
	name varchar(100) UNIQUE
);

CREATE TABLE IF NOT EXISTS product (
	product_id SERIAL PRIMARY KEY,
	name varchar(100),
	price int,
	description varchar(500),
	image varchar(500)
);

CREATE TABLE IF NOT EXISTS product_to_category (
	ptc_id SERIAL PRIMARY KEY,
	product_id int,
	category_id int,
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(product_id),
	CONSTRAINT category_fk FOREIGN KEY(category_id) REFERENCES category(category_id)
);

-- ROLE: 0 = customer, 1 = admin
CREATE TABLE IF NOT EXISTS account (
	account_id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULl,
	username varchar(100) UNIQUE NOT NULL,
	password varchar(500) NOT NULL,
	account_role int DEFAULT 0,
	address varchar(100)
);

CREATE TABLE IF NOT EXISTS cart_item (
	cart_item_id SERIAL PRIMARY KEY,
	account_id int,
	product_id int,
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(product_id),
	CONSTRAINT account_fk FOREIGN KEY(account_id) REFERENCES account(account_id)
);

CREATE TABLE IF NOT EXISTS order_item (
	order_item_id SERIAL PRIMARY KEY,
	account_id int,
	product_id int,
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(product_id),
	CONSTRAINT account_fk FOREIGN KEY(account_id) REFERENCES account(account_id)
);