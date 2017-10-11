package model;

import java.util.ArrayList;



/**
 * @author GustavoOliveira
 * @version v1.0.0 11/10/2017	
 * @since v1.0.0
 */
public class Admin {

	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	ArrayList<Admin> ListaAdm;

	public Admin() {
		ListaAdm = new ArrayList<>();
	}

	public Admin(String nome, String sobrenome, String email, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		ListaAdm = new ArrayList<>();
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
	
	public void info() {
		System.out.println("ADMIN");
		System.out.println("\tNome:" + this.getNome());
		System.out.println("\tSobrenome:" + this.getSobrenome());
		System.out.println("\tEmail:" + this.getEmail());
		System.out.println("\tSenha:" + this.getSenha());
	}

	public static Admin newInstance(Admin admin) {
		if(admin != null) {
			admin.getNome();
			admin.getSobrenome();
			admin.getEmail();
			admin.getSenha();
			admin.addAdm(admin);
		}
		return admin;
	}

}
