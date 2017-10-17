package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Admin {

	private String nome;
	private String sobrenome;
	private String cpf;
	private LocalDate dataNascimento;
	private String email;
	private String user;
	private String senha;
	private String confirmarSenha;
	ArrayList<Admin> ListaAdm;

	public Admin() {
		ListaAdm = new ArrayList<>();
	}

	public Admin(String nome, String sobrenome, LocalDate dataNasimento, String email, String cpf, String user, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNasimento;
		this.cpf = cpf;
		this.user = user;
		this.email = email;
		this.senha = senha;
		ListaAdm = new ArrayList<>();
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Admin> getListaAdm() {
		return ListaAdm;
	}

	public void setListaAdm(ArrayList<Admin> listaAdm) {
		ListaAdm = listaAdm;
	}
	
	public void addAdm(Admin a) {
		ListaAdm.add(a);
	}
	
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public String toString() {
		String toString = String.format("Admin(nome = %s, sobrenome = %s, email = %s, cpf = %s, user = %s)nome, sobrenome , email, cpf, user");
		return toString;
		
	}
	
	public void info() {
		System.out.println("ADMIN");
		System.out.println("\tNome:" + this.getNome());
		System.out.println("\tSobrenome:" + this.getSobrenome());
		System.out.println("\tData de Nascimento:" + this.getDataNascimento());
		System.out.println("\tEmail:" + this.getEmail());
		System.out.println("\tSenha:" + this.getSenha());
		System.out.println("");
	}

}