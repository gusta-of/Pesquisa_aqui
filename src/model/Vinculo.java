package model;

import java.io.Serializable;

public class Vinculo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Fornecedor idFornecedor;
	private Produto idProduto;
	private String marca;
	private double valor;

	public Vinculo() {
		super();
	}

	public Vinculo(Fornecedor idFornecedor, Produto idProduto, String marca, double valor) {
		this.idFornecedor = idFornecedor;
		this.idProduto = idProduto;
		this.marca = marca;
		this.valor = valor;
	}

	public Vinculo(Produto idProduto, String marca, double valor) {
		this.idProduto = idProduto;
		this.marca = marca;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fornecedor getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Fornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;

	}
}
