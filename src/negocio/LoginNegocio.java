package negocio;

import dao.LoginDao;

public class LoginNegocio {
	
	LoginDao loginDao = new LoginDao();
	
	public boolean verificaUsuarioEsenha() {
		return true;
	}
	
}
