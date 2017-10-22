package negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AdminDao;
import model.Admin;

public class AdminNegocio {
	AdminDao adminDao = new AdminDao();

	// ====================
	// Metodo Salvar
	// ====================
	public String salvar(Admin admin) throws ParseException {
		StringBuilder sb = new StringBuilder();
		String salvo = "";
		boolean senha = validarSenha(admin);
		boolean emailValido = false;
		boolean cpfValido = validaCPF(admin.getCpf());
		boolean valido = validarIdade(admin.getDataNascimento());
		emailValido = validarEmail(admin.getEmail());
		if (valido != true) {
			sb.append("Precisa ter mais de 18 anos");
		}
		if (cpfValido != true) {
			sb.append("\nCPF Inválido");
		}
		if(emailValido != true) {
			sb.append("\nEmail Inválido");
		}
		if(senha != true) {
			sb.append("\nSenhas não correspondentes");
		}
		
		if (sb.toString().equals("")) {
			salvo = "salvo";
		} else {
			salvo = sb.toString();
		}

		return salvo;

	}
	
	
	 public String editar(Admin admin) throws SQLException {

	        boolean cpfValido = false;
	        boolean emailValido = false;
	        String salvo = "falha";
	        StringBuilder sb = new StringBuilder();
	        cpfValido = validaCPF(admin.getCpf());
	        if (!cpfValido) {
	            sb.append("cpf invalido. \n");
	        }
	        emailValido = validarEmail(admin.getEmail());
	        if (!emailValido) {
	            sb.append("email invalido. \n");
	        }
	        if (sb.toString().isEmpty()) {
	            salvo = adminDao.Editar(admin);
	        } else {
	            sb.append(salvo);
	            return sb.toString();
	        }
	        sb.append(salvo);
	        return sb.toString();
	    }

	// ====================
	// Validador Idade
	// ====================
	public boolean validarIdade(LocalDate nascimento) throws ParseException {
		boolean valido = false;
		LocalDate dataAtual = LocalDate.now();
		Period idade = Period.between(nascimento, dataAtual);
		// System.out.println(idade.getYears() + " anos " + idade.getMonths() + " meses
		// e " + idade.getDays() + " dias");
		if (idade.getYears() > 18) {
			valido = true;
		} else if (idade.getYears() == 18) {
			if (idade.getMonths() > 0) {
				valido = true;
			} else if (idade.getMonths() == 0) {
				if (idade.getDays() >= 0) {
					valido = true;
				} else {
					valido = false;
					System.out.println("Você precisa ter mais de 18 anos!");
				}
			}
		}
		return valido;
	}

	// ===============
	// Validar CPF
	// ===============
	
    public boolean validaCPF(String CPF){

//considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

//"try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
//Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
//converte o i-esimo caractere do CPF em um numero:
//por exemplo, transforma o caractere '0' no inteiro 0
//(48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

//Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

//Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    //==============
  	// Validar email
  	//==============
	public boolean validarEmail(String email) {
		boolean validarE = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				validarE = true;
			}
		}
		return validarE;
	}
	//==============
	// Validar Senha
	//==============
	public boolean validarSenha(Admin admin) throws ParseException {
		boolean salvo = false;
		String senha1 = admin.getSenha();
		String senha2 = admin.getConfirmarSenha();
		if(senha1.equals(senha2)) {
			salvo = true;
		}else {
			salvo = false;
		}
		return salvo;
	}
	
	
	
	   public List<Admin> listarAdmin(){
	        List<Admin> admins = new ArrayList<Admin>();
	        admins = adminDao.listarAdmin();
	        return admins;
	    }
}

