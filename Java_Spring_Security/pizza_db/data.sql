
INSERT INTO customer (id_customer, name, address, email, phone_number)
VALUES
('863264988','Drake Theory','P.O. Box 136, 4534 Lacinia St.','draketheory@hotmail.com','(826) 607-2278'),
('617684636','Alexa Morgan','Ap #732-8087 Dui. Road','aleximorgan@hotmail.com','(830) 212-2247'),
('474771564','Johanna Reigns','925-3988 Purus. St.','johareigns@outlook.com','(801) 370-4041'),
('394022487','Becky Alford','P.O. Box 341, 7572 Odio Rd.','beckytwobelts@icloud.com','(559) 398-7689'),
('885583622','Brock Alford','9063 Aliquam, Road','brockalford595@platzi.com','(732) 218-4844'),
('531254932','Clarke Wyatt','461-4278 Dignissim Av.','wyattplay@google.co','(443) 263-8555'),
('762085429','Cody Rollins','177-1125 Consequat Ave','codyforchamp@google.com','(740) 271-3631'),
('363677933','Bianca Neal','Ap #937-4424 Vestibulum. Street','bianca0402@platzi.com','(792) 406-8858'),
('192758012','Drew Watson','705-6031 Aliquam Street','wangwatson@icloud.com','(362) 881-5943'),
('110410415','Mercedes Balor','Ap #720-1833 Curabitur Av.','mercedesbalorclub@hotmail.com','(688) 944-6619'),
('262132898','Karl Austin','241-9121 Fames St.','stonecold@icloud.com','(559) 596-3381'),
('644337170','Sami Rollins','Ap #308-4700 Mollis Av.','elgenerico@outlook.com','(508) 518-2967'),
('782668115','Charlotte Riddle','Ap #696-6846 Ullamcorper Avenue','amityrogers@outlook.com','(744) 344-7768'),
('182120056','Matthew Heyman','Ap #268-1749 Id St.','heymanboss@hotmail.com','(185) 738-9267'),
('303265780','Shelton Owens','Ap #206-5413 Vivamus St.','figthowens@platzi.com','(821) 880-6661');

INSERT INTO pizza (id_pizza, name, description, price, vegetarian, vegan, available)
VALUES
(1,'Pepperoni', 'Pepperoni, Homemade Tomato Sauce & Mozzarella.', 23.0, false, false, true),
(2,'Margherita', 'Fior de Latte, Homemade Tomato Sauce, Extra Virgin Olive Oil & Basil.', 18.5, true, false, true),
(3,'Vegan Margherita', 'Fior de Latte, Homemade Tomato Sauce, Extra Virgin Olive Oil & Basil.', 22.0, true, true, true),
(4,'Avocado Festival', 'Hass Avocado, House Red Sauce, Sundried Tomatoes, Basil & Lemon Zest.', 19.95, true, false, true),
(5,'Hawaiian', 'Homemade Tomato Sauce, Mozzarella, Pineapple & Ham.', 20.5, false, false, false),
(6,'Goat Chesse', 'Portobello Mushrooms, Mozzarella, Parmesan & Goat Cheeses with Alfredo Sauce.', 24.0, false, false, true),
(7,'Mother Earth', 'Artichokes, Roasted Peppers, Rapini, Sundried Tomatoes, Onion, Shaved Green Bell Peppers & Sunny Seasoning.', 19.5, true, false, true),
(8,'Meat Lovers', 'Mild Italian Sausage, Pepperoni, Bacon, Homemade Tomato Sauce & Mozzarella.', 21.0, false, false, true),
(9,'Marinated BBQ Chicken', 'Marinated Chicken with Cilantro, Red Onions, Gouda, Parmesan & Mozzarella Cheeses.', 20.95, false, false, false),
(10,'Truffle Cashew Cream', 'Wild mushrooms, Baby Kale, Shiitake Bacon & Lemon Vinaigrette. Soy free.', 22.0, true, true, true),
(11,'Rico Mor', 'Beef Chorizo, Sundried Tomatoes, Salsa Verde, Pepper, Jalapeno & pistachios', 23.0, false, false, true),
(12,'Spinach Artichoke', 'Fresh Spinach, Marinated Artichoke Hearts, Garlic, Fior de Latte, Mozzarella & Parmesan.', 18.95, true, false, true);

INSERT INTO pizza_order (id_customer, date, total, method, additional_notes)
VALUES
('192758012', NOW()-INTERVAL '5' DAY, 42.95, 'D', 'Don''t be late pls.'),
('474771564', NOW()-INTERVAL '4' DAY, 62.0, 'S', null),
('182120056', NOW()-INTERVAL '3' DAY, 22.0, 'C', null),
('617684636', NOW()-INTERVAL '2' DAY, 42.0, 'S', null),
('192758012', NOW()-INTERVAL '1' DAY, 20.5, 'D', 'Please bring the jalapeños separately.'),
('782668115', NOW(), 23, 'D', null);

INSERT INTO order_item (id_order, id_item, id_pizza, quantity, price)
VALUES
(1, 1, 1, 1, 23.0),
(1, 2, 4, 1, 19.95),
(2, 1, 2, 1, 18.5),
(2, 2, 6, 1, 24.0),
(2, 3, 7, 1, 19.5),
(3, 1, 3, 1, 22.0),
(4, 1, 8, 2, 42.0),
(5, 1, 10, 0.5, 11.0),
(5, 2, 12, 0.5, 9.5),
(6, 1, 11, 1, 23);

INSERT INTO users(username, disabled, email, locked, password)
VALUES
('admin', false, 'admin@abc.com', false, '$2y$10$N0Ja6VH3eleQy6vI9i/gw.SVSrFahBpYnYmt/zK30wSqmi30Qh4XW'),
('customer', false, 'customer@abc.com', false, '$2y$10$cnrogv23vBbZ9xA1lq0GFO6deKEPxZ8iPROYgF4ipU.WdcYEWGLjO');


INSERT INTO user_role(username, role, granted_date)
VALUES
('admin', 'ADMIN', NOW()),
('customer', 'CUSTOMER', NOW());



