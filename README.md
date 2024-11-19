Banco de dados bdremoto
```SQL
CREATE TABLE `noticia` (
	`idnoticia` INT(10) NOT NULL AUTO_INCREMENT,
	`autor` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci', 
	`titulo` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci', 
	`conteudo` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci', 
	`data` DATE NULL DEFAULT NULL, 
	PRIMARY KEY (`idnoticia`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
