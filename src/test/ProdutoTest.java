package test;

import model.Produto;

public class ProdutoTest {
	
	public static void main(String[] args) {
		Produto pro = new Produto();
		pro.setCodigo(12);
		pro.setNomeProduto("Arroz");
		pro.setDescricao("tipo 1");
		pro.setValor(10.99);
		pro.addPro(pro);
		pro.info();
		
		System.out.println();
		
		Produto pro1 = new Produto();
		pro1.setCodigo(11);
		pro1.setNomeProduto("Arroz");
		pro1.setDescricao("tipo 2");
		pro1.setValor(10.99);
		pro1.addPro(pro);
		pro1.info();

		System.out.println("Quantidade de produtos cadastrados:" + Produto.getContador());
	}
}
