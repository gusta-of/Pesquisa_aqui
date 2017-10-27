package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDao;
import model.Fornecedor;

public class FornecedorNegocio {

	private FornecedorDao fornecedorDao = new FornecedorDao();
	
	public List<Fornecedor> listarFornecedor(){
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores = fornecedorDao.listarFornecedor();
		return fornecedores;
	}
	
	public boolean salvar(Fornecedor fornecedor) {
		fornecedorDao.salvar(fornecedor);
		return true;
	}
	
	public boolean editar(Fornecedor fornecedor) {
		
		return fornecedorDao.editar(fornecedor);
	}
	
	public boolean deletar(Fornecedor fornecedor) {
		
		return fornecedorDao.deletar(fornecedor);
	}
	
}
