package controller;

import java.io.Serializable;
import java.text.ParseException;

import model.Admin;

public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void setarDadosAdmin() {
		Admin a = new Admin();
		a.setNome("");
		a.setSobrenome("");
		a.setEmail("");
		a.setSenha("");
	}
	
	public void salvarAdmin() throws ParseException, InstantiationException, IllegalAccessException {
		if(Admin.class.newInstance() != null) {
			
		}
	}

}
