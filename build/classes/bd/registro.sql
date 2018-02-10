CREATE SCHEMA IF NOT EXISTS registro;
USE registro;

CREATE TABLE computador(
 id INT AUTO_INCREMENT PRIMARY KEY,
 host VARCHAR(50),
 usuario VARCHAR(50),
 dia DATE,
 hora TIME
);

ALTER TABLE `computador` ADD UNIQUE `unique_index`(`host`, `usuario`, `dia`, `hora`);