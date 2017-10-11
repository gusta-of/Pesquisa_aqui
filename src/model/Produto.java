package model;

import java.util.ArrayList;

/**
 * @author GustavoOliveira
 * @version v1.0.0 11/10/2017	
 * @since v1.0.0
 */
public class Produto {

	private int codigo;
	private String nomeProduto;
	private String descricao;
	private double valor;
	private static int contador;
	ArrayList<Produto> ListaPro;

	public Produto() {
		super();
		ListaPro = new ArrayList<>();
		contador++;
	}
	
	public static int getContador() {
		return contador;
	}

	public Produto(int codigo, String nomeProduto, String descricao, double valor) {
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;
		this.descricao = descricao;
		this.valor = valor;
		ListaPro = new ArrayList<>();
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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
	
	public void info() {
		System.out.println("PRODUTO CADASTRADO");
		System.out.println("\tCodigo:" + this.getCodigo());
		System.out.println("\tNome do Produto:" + this.getNomeProduto());
		System.out.println("\tDescrição:" + this.getDescricao());
		System.out.println("\tValor:" + this.getValor());
	}

}
