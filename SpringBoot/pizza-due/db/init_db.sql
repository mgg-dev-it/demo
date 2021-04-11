DROP USER IF EXISTS pizzadue;
DROP DATABASE IF EXISTS pizzadue;

CREATE USER 'pizzadue'@'%' IDENTIFIED BY 'pizzadue';
CREATE DATABASE pizzadue DEFAULT CHARACTER SET 'utf8' DEFAULT COLLATE 'utf8_general_ci';
GRANT ALL PRIVILEGES ON pizzadue.* TO 'pizzadue'@'%';

SET PASSWORD FOR 'pizzadue'@'%' = 'pizzadue';

use pizzadue;

CREATE TABLE pizza (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), value INT, PRIMARY KEY (id));

INSERT INTO pizza (name, value) VALUES ('Margherita', 100);
INSERT INTO pizza (name, value) VALUES ('Salame', 150);
INSERT INTO pizza (name, value) VALUES ('Prosciutto', 220);

select * from pizza;

-- SET GLOBAL time_zone = '+2:00';