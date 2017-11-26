package model;

import java.util.ArrayList;

public class Fornecedor {

	private int id;
	private String nome;
	private String endereco;
	ArrayList<Fornecedor> listaFornecedor;
	
	public Fornecedor() {
	   super();
		listaFornecedor = new ArrayList<>(); 
	}
	
	public Fornecedor (String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		listaFornecedor = new ArrayList<>();
	}
	
	public Fornecedor(String nome) {
		this.nome = nome;
	}
			
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
