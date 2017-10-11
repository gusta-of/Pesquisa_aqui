package negocio;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

public class AdminNegocio {

	// ====================
	// Metodo Salvar
	// ====================
	public String salvar() {
		return null;
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
	public String validarCampos() {
		return null;
	}

}