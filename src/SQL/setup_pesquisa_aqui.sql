use mysql;

DROP TABLE IF EXISTS pesquisa_aqui;

create database pesquisa_aqui;

use pesquisa_aqui;

CREATE TABLE admin (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	usuario VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	cpf VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	confirmarSenha VARCHAR(100) NOT NULL,
	dataNascimento DATE NOT NULL
);

INSERT INTO admin (nome, sobrenome, usuario, email, cpf, senha, confirmarSenha, dataNascimento)
VALUES ("teste", "teste", "teste", "teste@teste.com", "04393906136", "123", "123", "1997-12-09");