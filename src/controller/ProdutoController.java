package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Fornecedor;
import model.Produto;
import negocio.ProdutoNegocio;

public class ProdutoController implements Serializable, Initializable {

	private static final long serialVersionUID = 1L;

	private ObservableList<Produto> listProdutos = FXCollections.observableArrayList();

	@FXML
	private AnchorPane acPane;

	@FXML
	private Pane pnPane;

	@FXML
	private Label LbTituloPrincipal, lbcodigo, lbdescricao, lbTituloTabela, lbAtacado, lbProduto, lbMsgSup,
			lbMsgProduto, lbMsgCod, lbMsgDesc;

	@FXML
	private TextField txproduto, txdescricao, txvalor, txcodigo, txatacado, txMarca;

	@FXML
	private ComboBox<Fornecedor> cbFornecedores;

	@FXML
	private TableView<Produto> tbtabela;

	@FXML
	private TableColumn<Produto, String> colProduto;

	@FXML
	private TableColumn<Produto, String> colDescricao;

	@FXML
	private TableColumn<Produto, String> colCodigo;

	@FXML
	private Button btcancelar, btsalvar, btMain, btCadFornecedor, btCadAdmin;

	@FXML
	private ImageView imgLogo, imgInicio, imgCadFornecedor, imgCadAdmin;

	ProdutoNegocio produtoNegocio = new ProdutoNegocio();
	List<Produto> produtos = new ArrayList<Produto>();
	List<Produto> pro = produtoNegocio.listarProduto();
	
	Produto produto = new Produto();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarProduto();
		// selecionarFornecedor();
	}

	public void listarProduto() {
		if (!listProdutos.isEmpty()) {
			listProdutos.clear();
		}

		for (Produto produto : pro) {
			Produto p = new Produto(produto.getNomeProduto(), produto.getCodigo(), produto.getDescricao());
			listProdutos.add(p);
		}

		colProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("nomeProduto"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
		
		tbtabela.setItems(listProdutos);

	}

	@FXML
	public void salvar() {
		ProdutoNegocio pn = new ProdutoNegocio();
		boolean validar = false;
		if (produto.getId() == 0) {
			setarDadosProd();
			validar = validarCampos(produto);
			if (validar == false) {
				validarCampos(produto);
				lbMsgCod.setVisible(true);
				lbMsgProduto.setVisible(true);
				lbMsgDesc.setVisible(true);
			} else {
				if (pn.salvar(produto).equals("salvo")) {
					produtos.add(produto);
					pn.salvar(produto);
					listarProduto();
				} else {
					lbMsgCod.setVisible(false);
					lbMsgProduto.setVisible(false);
					lbMsgDesc.setVisible(false);
					limparCampos();
				}
			}
		} else {
			setarDadosProd();
			pn.salvar(produto);
			listarProduto();
			limparCampos();
		}

	}

	public void setarDadosProd() {
		// produto.setIdFornecedor(cbFornecedores.getValue());
		// if (cbFornecedores.getValue() != null) {
		// FornecedorNegocio fn = new FornecedorNegocio();
		// List<Fornecedor> forn = new ArrayList<>();
		// forn = fn.listarFornecedor();
		// for (int i = 0; i < forn.size(); i++) {
		// if (forn.get(i).getNome().equals(cbFornecedores.getValue().toString())) {
		// produto.setIdFornecedor(forn.get(i));
		// }
		// }
		// }

		produto.setNomeProduto(txproduto.getText());
		produto.setCodigo(Integer.parseInt(txcodigo.getText()));
		produto.setDescricao(txdescricao.getText());
	}

	public void limparCampos() {
		txproduto.setText("");
		txcodigo.setText("");
		txdescricao.setText("");
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

	public void irParaMain() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Main adm.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}

	public Boolean validarCampos(Produto produto) {
		StringBuilder inconsistencias = new StringBuilder();
		if (produto.getCodigo() <= 0) {
			inconsistencias.append("\nO produto n�o pode ser cadastrado com um codigo menor ou igual a 0.");
			lbMsgCod.setText("Campo Obrigatório");
		}
		if (produto.getNomeProduto().equals("") || produto.getNomeProduto() == null) {
			inconsistencias.append("\nO nome do produto � obrigat�rio.");
			lbMsgProduto.setText("Campo Obrigatório");
		}
		if (produto.getDescricao().equals("") || produto.getDescricao() == null) {
			inconsistencias.append("\nA descri��o do produto � obrigat�rio.");
			lbMsgDesc.setText("Campo Obrigatório");
		}
		System.out.println(inconsistencias.toString());
		return true;
	}

	// FornecedorNegocio fn = new FornecedorNegocio();
	//
	// public void selecionarFornecedor() {
	// List<Fornecedor> list = fn.listarFornecedorNome();
	// cbFornecedores.getItems().clear();
	// for (int i = 0; i < list.size(); i++) {
	// System.out.println(list.get(i).toString());
	// cbFornecedores.getItems().addAll(list.get(i));
	// }
	//
	// }

}