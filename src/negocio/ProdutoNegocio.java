package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoNegocio {
	
	ProdutoDao produtoDao = new ProdutoDao();

	public String salvar() {

		return null;
	}

	public List<Produto> listarProduto() {
		List<Produto> produtos  = new ArrayList<Produto>();
		produtos = produtoDao.listarProduto();
		return produtos;
	}

}
