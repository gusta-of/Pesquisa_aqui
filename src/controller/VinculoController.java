package controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Fornecedor;
import model.Produto;
import model.Vinculo;
import negocio.FornecedorNegocio;
import negocio.ProdutoNegocio;
import negocio.VinculoNegocio;

public class VinculoController implements Serializable, Initializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Button btInicioS, btAjudaS, btSave;

	@FXML
	private AnchorPane acPane;

	@FXML
	private ImageView imgLogo;

	@FXML
	private ComboBox<Fornecedor> cbMercado;

	@FXML
	private ComboBox<Produto> cbProduto;

	@FXML
	private TextField txMarca, txValor;

	@FXML
	private Label lbMarca, lbValor, lbMercado, lbProduto, lbMV;

	VinculoNegocio vn = new VinculoNegocio();
	Vinculo v = new Vinculo();
	Vinculo vinculo = new Vinculo();
	List<Vinculo> vinculos = new ArrayList<>();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarVinculo();
		selecionarFornecedor();
		selecionarProduto();
	}

	public List<Vinculo> listarVinculo() {
		vinculos = vn.listarVinculo();
		return vinculos;
	}

	@FXML
	public void salvar() {
		setarDadosVinculo();
		vinculos.add(vinculo);
		vn.salvar(vinculo);

	}

	public void setarDadosVinculo() {

		vinculo.setIdFornecedor(cbMercado.getValue());
		if (cbMercado.getValue() != null) {
			FornecedorNegocio fn = new FornecedorNegocio();
			List<Fornecedor> listf = new ArrayList<>();
			listf = fn.listarFornecedor();
			Fornecedor f = new Fornecedor();
			f = cbMercado.getSelectionModel().getSelectedItem();
			vinculo.setIdFornecedor(f);
		}
		vinculo.setIdProduto(cbProduto.getValue());
		if (cbProduto.getValue() != null) {
			ProdutoNegocio pn = new ProdutoNegocio();
			List<Produto> listP = new ArrayList<>();
			listP = pn.listarProduto();
			Produto p = new Produto();
			p = cbProduto.getSelectionModel().getSelectedItem();
			vinculo.setIdProduto(p);
		}
		vinculo.setIdFornecedor(cbMercado.getValue());
		vinculo.setIdProduto(cbProduto.getValue());
		vinculo.setMarca(txMarca.getText());
		String valor = txValor.getText();
		valor = valor.replaceAll(",", ".");
		vinculo.setValor(Double.parseDouble(valor));

	}

	public void selecionarFornecedor() {
		FornecedorNegocio fn = new FornecedorNegocio();

		List<Fornecedor> listf = fn.listarFornecedor();
		cbMercado.getItems().clear();
		cbMercado.getItems().addAll(listf);
	}

	public void selecionarProduto() {
		ProdutoNegocio pn = new ProdutoNegocio();

		List<Produto> listP = pn.listarProduto();
		cbProduto.getItems().clear();
		cbProduto.getItems().addAll(listP);
	}

}
