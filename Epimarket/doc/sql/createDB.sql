DROP database IF EXISTS epimarket;
CREATE database epimarket;
use epimarket;

CREATE TABLE client(id int NOT NULL AUTO_INCREMENT, login VARCHAR(255), password CHAR(255), firstName VARCHAR(255), middleName VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255), phone VARCHAR(255), streetNumber VARCHAR(255), streetName VARCHAR(255), city VARCHAR(255), zipCode VARCHAR(255), country VARCHAR(255),
						PRIMARY KEY (id)
						);

						
CREATE TABLE category(id int NOT NULL AUTO_INCREMENT, name VARCHAR(255),
						PRIMARY KEY (id)
						);

CREATE TABLE orders(id int NOT NULL AUTO_INCREMENT, clientId int, dateOrderStarted DATE, dateOrderEnded DATE,
					PRIMARY KEY (id),
					FOREIGN KEY (clientId) REFERENCES client(id)
					);

CREATE TABLE product(id int NOT NULL AUTO_INCREMENT, designation VARCHAR(255), categoryId int, price int, description VARCHAR(255),
						PRIMARY KEY (id),
						FOREIGN KEY (categoryId) REFERENCES category(id)
						);
						
CREATE TABLE orderline(id int NOT NULL AUTO_INCREMENT, ordersId int, productId int, quantity int,
							PRIMARY KEY (id),
							FOREIGN KEY (ordersId) REFERENCES orders(id),
							FOREIGN KEY (productId) REFERENCES product(id)
							);