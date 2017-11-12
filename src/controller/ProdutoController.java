package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Produto;
import negocio.ProdutoNegocio;

public class ProdutoController implements Serializable, Initializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane acPane;
	
	@FXML
	private Pane pnPane;
	
	@FXML
	private Label LbTituloPrincipal, lbcodigo, lbdescricao, lbvalor, lbTituloTabela, lbAtacado, lbMarca, lbProduto,
			lbMsgSup, lbMsgProduto, lbMsgMarca, lbMsgCod, lbMsgDesc, lbMsgValor;

	@FXML
	private TextField txproduto, txdescricao, txvalor, txcodigo, txatacado, txMarca;

	@FXML
	private TableView<Produto> tbtabela;

	@FXML
	private TableColumn<Produto, String> tbproduto;

	@FXML
	private TableColumn<Produto, String> tbvalor;

	@FXML
	private TableColumn<Produto, String> tbdescricao;

	@FXML
	private TableColumn<Produto, String> tbcodigo;

	@FXML
	private Button btcancelar, btsalvar, btInicio, btCadFornecedor, btCadAdmin;

	@FXML
	private ImageView imgLogo, imgInicio, imgCadFornecedor, imgCadAdmin;

	ProdutoNegocio produtoNegocio = new ProdutoNegocio();
	List<Produto> produtos = new ArrayList<Produto>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Produto> produtoList = listarProduto();
		populaView(produtoList);
	}

	private List<Produto> listarProduto() {
		produtos = produtoNegocio.listarProduto(); // Implementar em Negocio
		return produtos;
	}

	public void populaView(List<Produto> porduto) {

		// Implementar

	}

	public void irParaFornecedor() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de fornecedor.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaAdmin() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de administrador.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaInicio() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Inicio.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
}