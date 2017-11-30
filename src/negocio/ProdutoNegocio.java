package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoNegocio {
	
	ProdutoDao produtoDao = new ProdutoDao();

	public String salvar(Produto produto) {
		String salvo = "";
		StringBuilder sb = new StringBuilder();
		if (validarValor() == true) {
			sb.append("\nO produto n�o pode ser cadastrado com valor menor ou igual a 0.");
		}
		if (sb.toString().equals("")) {
			   produtoDao.salvar(produto);
			} else {
				salvo = sb.toString();
			}
		
		return salvo;
	}

	public List<Produto> listarProduto() {
		List<Produto> produtos  = new ArrayList<Produto>();
		produtos = produtoDao.listarProduto();
		return produtos;
	}
	
	public List<Produto> listarProdutoPorNome() {
		List<Produto> produtos  = new ArrayList<Produto>();
		produtos = produtoDao.listarProdutoPorNome();
		return produtos;
	}
	
	public boolean validarValor() {
//		Produto p = new Produto();
		boolean validar = false;
//		if(p.getValor() <= 0 ) {
//			validar = false;
//		}else {
//			validar = true;
//		}
		return validar;
	}
	
}
