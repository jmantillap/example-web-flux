CREATE TABLE IF NOT EXISTS product (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45), 
	price FLOAT DEFAULT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY name_UNIQUE (name)
	);
	
CREATE TABLE IF NOT EXISTS `user`
( `id` INT UNSIGNED NOT NULL AUTO_INCREMENT , `username` VARCHAR(10) NOT NULL , `email` VARCHAR(50) NOT NULL , `password` VARCHAR(255) NOT NULL, `roles` VARCHAR(25) NOT NULL, PRIMARY KEY (`id`), UNIQUE `username_unique` (`username`), UNIQUE `email_unique` (`email`)) ENGINE = InnoDB;