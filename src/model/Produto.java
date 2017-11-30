package model;

import java.util.ArrayList;

public class Produto {

	private int id;
	private int codigo;
	private String nomeProduto;
	private String descricao;
	ArrayList<Produto> ListaPro;

	public Produto() {
		ListaPro = new ArrayList<>();
	}

	public Produto(String nomeProduto, int codigo, String descricao) {
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;
		this.descricao = descricao;
		ListaPro = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Produto> getListaPro() {
		return ListaPro;
	}

	public void setListaPro(ArrayList<Produto> listaPro) {
		ListaPro = listaPro;
	}

	public void addPro(Produto p) {
		ListaPro.add(p);
	}

}
