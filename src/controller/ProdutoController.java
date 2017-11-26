package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.FornecedorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	private ChoiceBox<Fornecedor> cbCb;

	@FXML
	private TableView<Produto> tbtabela;

	@FXML
	private TableColumn<Produto, String> colProduto;

	@FXML
	private TableColumn<Produto, String> colValor;

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

	Produto produto = new Produto();

	Fornecedor f = new Fornecedor();
	ObservableList<Fornecedor> obFornecedor = FXCollections.observableArrayList(f);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Produto> produtoList = listarProduto();
		populaView(produtoList);
		// selecionarFornecedor();
		for (int i = 0; i < obFornecedor.size(); i++) {
			cbCb.setItems(obFornecedor);
		}
	}

	private List<Produto> listarProduto() {
		produtos = produtoNegocio.listarProduto(); // Implementar em Negocio
		return produtos;
	}

	@FXML
	public void salvar() {
		ProdutoNegocio pn = new ProdutoNegocio();
		boolean validar = false;
		if (produto.getId() == 0) {
			setarDadosProd();
			validar = validarCampos(produto);
			if (validar == true) {
				validarCampos(produto);
				lbMsgCod.setVisible(true);
				lbMsgProduto.setVisible(true);
				lbMsgDesc.setVisible(true);
				lbMsgValor.setVisible(true);
			} else if (pn.salvar(produto).equals("salvo")) {
				produtos.add(produto);
				this.populaView(produtos);
				this.limparCampos();
				lbMsgCod.setVisible(false);
				lbMsgProduto.setVisible(false);
				lbMsgDesc.setVisible(false);
				lbMsgValor.setVisible(false);
			}
		} else {
			setarDadosProd();
			pn.salvar(produto);
			listarProduto();
			this.populaView(produtos);
			this.limparCampos();
		}

	}

	public void setarDadosProd() {
		produto.setNomeProduto(txproduto.getText());
		produto.setMarca(txMarca.getText());
		produto.setValor(Double.parseDouble(txvalor.getText()));
		produto.setCodigo(Integer.parseInt(txcodigo.getText()));
		produto.setDescricao(txdescricao.getText());
	}

	public void limparCampos() {
		txproduto.setText("");
		txMarca.setText("");
		txvalor.setText("");
		txcodigo.setText("");
		txdescricao.setText("");
	}

	public void populaView(List<Produto> produtos) {
		colProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("Produto"));
		colValor.setCellValueFactory(new PropertyValueFactory<Produto, String>("Valor"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("Descricao"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("Codigo"));
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
		if (produto.getDescricao().equals("") || produto.getDescricao() == null) {
			inconsistencias.append("\nO produto n�o pode ser cadastrado com valor menor ou igual a 0.");
			lbMsgValor.setText("Campo Obrigatório");
		}
		System.out.println(inconsistencias.toString());
		return true;
	}

	// public void carregaComboModelos() throws ClassNotFoundException {
	// VeiculoControl v = new VeiculoControl();
	// ArrayList<ModeloVeiculo> modelos = new
	// ArrayList<ModeloVeiculo>(v.pegaModelos());
	// for (ModeloVeiculo m : modelos) {
	// comboModelo.addItem(m); /*detalhe - comboModelo é o nome de meu obj swing
	// }
	// }

	// private List<Fornecedor> listaF = new ArrayList<>();

	// private ObservableList<Fornecedor> obFornecedor =
	// FXCollections.observableArrayList();
	// FornecedorDao fd = new FornecedorDao();
	// private List<Fornecedor> listaF = fd.listarFornecedor();
	//
	// public void selecionarFornecedor() {
	// List<Fornecedor> fornecedor = new ArrayList<>();
	//
	// if(!obFornecedor.isEmpty()) {
	// obFornecedor.clear();
	// }
	//
	// for (Fornecedor f : listaF) {
	//
	// Fornecedor f1 = new Fornecedor(f.getNome());
	// obFornecedor.add(f1);
	//
	// }
	//
	// obFornecedor = FXCollections.observableArrayList(fornecedor);
	// cbCb.setItems(obFornecedor);
	// }

}