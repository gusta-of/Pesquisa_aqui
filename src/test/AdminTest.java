package test;

import java.util.Date;

import model.Admin;

public class AdminTest {

	public static void main(String[] args) {
		Admin adm = new Admin();
		adm.setNome("Gustavo");
		adm.setSobrenome("de Oliveira");
		adm.setDataNascimento(new Date());
		adm.setEmail("gustavo@gustavo.com");
		adm.setSenha("12345");
		adm.addAdm(adm);
		adm.info();
	}

}