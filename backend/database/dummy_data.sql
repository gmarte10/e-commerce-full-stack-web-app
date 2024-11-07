SET SEARCH_PATH TO ecommerce_schema;

INSERT INTO account (name, username, password, address)
VALUES 
	(
	'John Doe',
	'johndoe34@gmail.com',
	'$2a$12$GTTpwqerXlWwEdxfcqdnHu/twcmNQVbm/doHo/WDd7XNdSk3JlaG6',
	'Arkham Asylum, Gotham'
	);

INSERT INTO account (name, username, password)
VALUES 	
	(
	'Jane Doe',
	'janedoe499@gmail.com',
	'$2a$12$GlXXZdzTHvk8IB3t1rq27eE7glIWjTbwb/ArLOVcxywXcaW59ZaT6'
	);

INSERT INTO account (name, username, password, account_role)
VALUES
	(
	'Bruce Wayne',
	'brucewayne@gmail.com',
	'$2a$12$hNDiq2a8rHFxggCngAwJF.uqpAn01UJYEzYt570sXOycWhlN1aP1O',
	1
	);

INSERT INTO product (name, price, description, image)
VALUES
	(
	'iphone 15',
	1000,
	'The iPhone 15 features USB-C, A16 chip, 48MP camera, and iOS 17, with improved performance.',
	'iphone15.jpg'
	),
	(
	'hoodie',
	50,
	'A cozy hoodie with a soft interior, adjustable hood, and a front pocket for comfort.',
	'hoodie.jpg'
	),
	(
	'apple',
	2,
	'A crisp, sweet fruit packed with vitamins, fiber, and antioxidants for a healthy snack.',
	'apple.jpg'
	),
	(
	'fish bowl',
	10,
	'A small, round aquarium for keeping fish, often with decorative stones and plants inside.',
	'fish-bowl.jpg'
	);

INSERT INTO cart_item (account_id, product_id)
VALUES 
	(1, 3),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2);

INSERT INTO order_item (account_id, product_id)
VALUES 
	(2, 3),
	(1, 2),
	(2, 3),
	(2, 1),
	(1, 2);