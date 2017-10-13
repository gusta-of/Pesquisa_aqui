use mysql;

DROP TABLE IF EXISTS pesquisa_aqui;

create database pesquisa_aqui;

use pesquisa_aqui;

CREATE TABLE ADMIN (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	usuario VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	cpf VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	dataNascimento DATE NOT NULL
);