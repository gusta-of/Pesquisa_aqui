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
VALUES ("Root", "teste", "root", "teste@teste.com", "04393906136", "rootroot", "rootroot", "1997-12-09");

CREATE TABLE IF NOT EXISTS fornecedores(
   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (255) NOT NULL,
    endereco VARCHAR (255) NOT NULL
);

INSERT INTO fornecedores(nome, endereco)
VALUES ("teste", "Rua teste");

CREATE TABLE IF NOT EXISTS produto (
   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   codigo INT(100) NOT NULL,
   nomeProduto VARCHAR(200) NOT NULL,
   descricao VARCHAR(200) NOT NULL 
);

CREATE TABLE IF NOT EXISTS vinculo (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   idProduto INT(15) NOT NULL,
   idFornecedor INT(15) NOT NULL,
   valor DOUBLE(15,2) NOT NULL,
   marca VARCHAR(20) NOT NULL,
   CONSTRAINT fk_produto FOREIGN KEY (idProduto) REFERENCES produto(id),
   CONSTRAINT fk_fornecedor FOREIGN KEY (idFornecedor) REFERENCES fornecedores (id)
);

USE pesquisa_aqui;

/*
 * consulta Inner Join
 * */
SELECT produto.nomeProduto, fornecedores.nome FROM vinculo INNER JOIN produto ON produto.id = vinculo.idProduto INNER JOIN fornecedores ON fornecedores.id = vinculo.idFornecedor;
SELECT produto.nomeProduto, produto.descricao FROM vinculo INNER JOIN produto ON produto.id = vinculo.idProduto;