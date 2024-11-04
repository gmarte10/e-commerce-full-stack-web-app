SET SEARCH_PATH TO ecommerce_schema;

INSERT INTO account (name, username, password)
VALUES 
	(
	'John Doe',
	'johndoe34@gmail.com',
	'joker!4'
	),
	(
	'Jane Doe',
	'janedoe499@gmail.com',
	'$7harley'
	);

INSERT INTO product (name, price)
VALUES
	('iphone 15',1000),
	('hoodie',50),
	('apple',2),
	('jeans',60),
	('fish bowl',10),
	('straws', 2),
	('socks', 8);

INSERT INTO cart_item (account_id, product_id)
VALUES 
	(1, 3),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2);