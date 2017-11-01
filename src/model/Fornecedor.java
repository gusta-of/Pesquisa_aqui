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
	
	public Fornecedor (int id, String nome, String endereco) {
	   super();
		id = this.id;
		nome = this.nome;
		endereco = this.endereco;
		listaFornecedor = new ArrayList<>();
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
