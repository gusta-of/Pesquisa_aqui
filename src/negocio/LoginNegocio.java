package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.LoginDao;
import model.Admin;

public class LoginNegocio {

	LoginDao usuario = new LoginDao();

	public List<Admin> listarAdmin() {
		List<Admin> admins = new ArrayList<>();
		admins = usuario.listarAdmin();
		return admins;
	}

	public String validarLog() throws IOException {
		String login = "falha";
		if (usuario == null) {
			System.out.println("Erro na lista de usuario!");
			login = "falhou";
		} else {
			login = "logado";
		}
		return login;

	}

}
