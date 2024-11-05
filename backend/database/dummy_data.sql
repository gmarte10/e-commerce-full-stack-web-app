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