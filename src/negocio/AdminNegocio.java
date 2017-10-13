package negocio;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

import dao.AdminDao;
import model.Admin;

public class AdminNegocio {

	// ====================
	// Metodo Salvar
	// ====================
	public String salvar(Admin admin) throws ParseException {
		String salvo = "";
		String camposValidos = "";
		boolean valido = validarIdade(admin.getDataNascimento());
		camposValidos = validarCampos(admin);
		if(valido) {
			if(camposValidos.equals("") && camposValidos != null) {
					AdminDao admindao = new AdminDao();
					salvo = admindao.salvar(admin);
		}}else {
			salvo = "O usuário precisar ter mais de 18 anos!";
		}
		return salvo;
	}

	// ====================
	// Validador Idade
	// ====================
	public boolean validarIdade(LocalDate nascimento) throws ParseException {
		boolean valido = false;
		LocalDate dataAtual = LocalDate.now();
		Period idade = Period.between(nascimento, dataAtual);
		System.out.println(idade.getYears() + " anos " + idade.getMonths() + " meses e " + idade.getDays() + " dias" );
		if(idade.getYears() > 18) {
			valido = true;
		}else if(idade.getYears() == 18) {
			if(idade.getMonths() > 0) {
				valido = true;
			} else if(idade.getMonths() == 0) {
				if(idade.getDays() >= 0) {
					valido = true;
				} else {
					valido = false;
					System.out.println("Você precisa ter mais de 18 anos!");
				}
			}
		}
		return valido;
	}
	
	// ====================
	// Validador Campos
	// ====================
	public String validarCampos(Admin admin) {
		StringBuilder inconsistencias = new StringBuilder();
		if(admin.getNome().equals("") || admin.getNome() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getSobrenome().equals("") || admin.getSobrenome() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getUser().equals("") || admin.getUser() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getEmail().equals("") || admin.getEmail() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getCpf().equals("") || admin.getCpf() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getSenha().equals("") || admin.getSenha() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		if(admin.getConfirmarSenha().equals("") || admin.getConfirmarSenha() == null) {
			inconsistencias.append("\n Campo obrigatório");
		}
		System.out.println(inconsistencias.toString());
		return  inconsistencias.toString();
	}
}

