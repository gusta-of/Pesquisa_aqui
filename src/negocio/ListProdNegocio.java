package negocio;

import java.util.List;

import dao.VinculoDao;
import model.Vinculo;

public class ListProdNegocio {
	VinculoDao vd = new VinculoDao();
	
	public List<Vinculo> listarVinculo() {
		List<Vinculo> listVinculo = vd.listarVinculo();
		return listVinculo;
	}
}
