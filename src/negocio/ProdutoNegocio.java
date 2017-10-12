package negocio;

import model.Produto;

public class ProdutoNegocio {
	
	public String salvar() {
		return null;
	}
	
	public boolean validarIdade() {
		return true;
	}
	
	public String validarCampos(Produto produto) {
		StringBuilder inconsistencias = new StringBuilder();
		if(produto.getCodigo() <= 0) {
			inconsistencias.append("\nO produto não pode ser cadastrado com um codigo menor ou igual a 0.");
		}
		if(produto.getNomeProduto().equals("") || produto.getNomeProduto() == null) {
			inconsistencias.append("\nO nome do produto é obrigatório.");
		}
		if(produto.getDescricao().equals("") || produto.getDescricao() == null) {
			inconsistencias.append("\nA descrição do produto é obrigatório.");
		}
		if(produto.getValor() <= 0) {
			inconsistencias.append("\nO produto não pode ser cadastrado com valor menor ou igual a 0.");
		}
		System.out.println(inconsistencias.toString());
		return  inconsistencias.toString();
	}

}
