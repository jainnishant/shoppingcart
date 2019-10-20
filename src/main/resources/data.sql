DROP TABLE IF EXISTS Discounts;

CREATE TABLE Discounts(
	id INT AUTO_INCREMENT PRIMARY KEY,
	from_value NUMBER NOT NULL,
	to_value NUMBER,
	discount_percentage NUMBER);

INSERT INTO Discounts (from_value, to_value, discount_percentage) values 
(0, 5000, 0),
(5000, 10000, 10.0),
(10000, null, 20.0);