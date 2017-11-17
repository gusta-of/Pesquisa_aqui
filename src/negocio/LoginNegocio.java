package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import dao.LoginDao;
import model.Admin;

public class LoginNegocio {

	LoginDao usuario = new LoginDao();

	public List<Admin> listarAdmin() {
		List<Admin> admins = new ArrayList<>();
		admins = usuario.listarAdmin();
		return admins;
	}

//	public String validarLog(Admin admin) throws IOException {
//		String login = "falha";
//		if (verificaSeEUsuario() == false) {
//			System.out.println("Erro na lista de usuario!");
//			login = "falhou";
//		} else {
//			login = "logado";
//		}
//		return login;
//
//	}

}
