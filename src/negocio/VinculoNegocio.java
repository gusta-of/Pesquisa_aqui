package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import dao.VinculoDao;
import model.Vinculo;

public class VinculoNegocio implements Serializable {

	private static final long serialVersionUID = 1L;

	VinculoDao vinculo = new VinculoDao();

	public List<Vinculo> listarVinculo() {
		List<Vinculo> e = new ArrayList<>();
		e = vinculo.listarVinculo();
		return e;
	}

	public String salvar() {
		return "salvo";
	}
}
