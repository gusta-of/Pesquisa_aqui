package negocio;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.AdminDao;
import model.Admin;

public class AdminNegocio {
	
	AdminDao adminDao = new AdminDao();

	// ====================
	// Metodo Salvar
	// ====================
	public String salvar(Admin admin) throws ParseException {
		boolean CPF  = false;
		String salvo = "falha";
        StringBuilder sb = new StringBuilder();
        CPF = validaCPF(admin.getCpf());
        if(!CPF) {
        	sb.append("CPF invalido \n");
        }
        if (sb.toString().isEmpty()) {
            salvo = adminDao.editar(admin);
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


	public String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}
	
	   public List<Admin> listarAdmin(){
	        List<Admin> admins = new ArrayList<Admin>();
	        admins = adminDao.listarAdmin();
	        return admins;
	    }
}

