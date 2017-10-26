use mysql;

DROP DATABASE IF EXISTS pesquisa_aqui;

CREATE DATABASE IF NOT EXISTS pesquisa_aqui;

USE pesquisa_aqui;

CREATE TABLE IF NOT EXISTS admin (
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

CREATE TABLE IF NOT EXISTS fornecedores(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (255) NOT NULL,
    endereco VARCHAR (255) NOT NULL
);

INSERT INTO fornecedores(nome, endereco)
VALUES ("teste", "Rua teste");
