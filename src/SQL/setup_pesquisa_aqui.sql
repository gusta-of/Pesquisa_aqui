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
VALUES ("Makro", "Av. Perimetral Norte"),
		("Assai", "Av. Perimetral Norte"),
		("Bretas", "Passeio das águas - Shopping"),
		("Barão", "Cidade Jardim");

CREATE TABLE IF NOT EXISTS produto (
   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   codigo INT(100) NOT NULL,
   nomeProduto VARCHAR(200) NOT NULL,
   descricao VARCHAR(200) NOT NULL 
);

INSERT INTO produto(codigo, nomeProduto, descricao)
VALUES (1, "Carne", "5Kg Fraldinha"),
		(2, "Açúcar", "2 pacotes, tipo refinado"),
		(3, "Arroz", "5Kg, tipo agulinha"),
		(4, "Biscoito", "Recheado"),
		(5, "Café", "250g"),
		(6, "Farofa", "250g, tipo mandioca"),
		(7, "Farinha", "1Kg, tipo trigo"),
		(8, "Feijão", "1Kg, 2 Pacotes"),
		(9, "Fubá", "500g"),
		(10, "Goiabada", "300g"),
		(11, "Macarrão", "com ovos, tipo espaguete, 500g"),
		(12, "Óleo", "2 litros, tipo soja, 900ml"),
		(13, "Molho", "tipo tomate, 340g"),
		(14, "Sal", "1Kg"),
		(15, "Vinagre", "750ml"),
		(16, "Tempero", "tipo completo, 300g"),
		(17, "Creme", "tipo dental, 90g"),
		(18, "Papel", "tipo higiênico, pacote c/ 4 unidades"),
		(19, "Sabonete", "pacote c/4 unidades"),
		(20, "Detergente","tipo líquido, 500ml"),
		(21, "Lã de aço", "60g"),
		(22, "Sabão", "em pedra c/ 5 unidades, 200ml"),
		(23, "Sabão", "em pó, 500ml");


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