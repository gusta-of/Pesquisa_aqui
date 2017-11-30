package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import dao.VinculoDao;
import model.Vinculo;

public class VinculoNegocio implements Serializable {

	private static final long serialVersionUID = 1L;

	VinculoDao vinculoD = new VinculoDao();
	Vinculo v = new Vinculo();

	public List<Vinculo> listarVinculo() {
		List<Vinculo> e = new ArrayList<>();
		e = vinculoD.listarVinculo();
		return e;
	}

	public String salvar(Vinculo vinculo) {
		String validar = "";
//		if(v.getValor() <= 0) {
//			validar = "False";
//		}else {
		vinculoD.salvar(vinculo);
		validar = "Salvo";
//		}
		return validar;
	}
}
