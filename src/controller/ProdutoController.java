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
import negocio.FornecedorNegocio;
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
	private ComboBox<Fornecedor> cbFornecedores;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Produto> produtoList = listarProduto();
		populaView(produtoList);
		selecionarFornecedor();
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

	// comboBox.removeAllItems(); //remove os itens atuais do comboBox.
	// ArrayList produtos = produtoDAO.findAll(); //'produtoDAO' é meu objeto que
	// retorna os produtos do banco.
	// Iterator i = produtos.iterator();
	// while(i.hasNext()) {
	// comboBox.addItem(String.valueOf(i.next()));
	// }

	// FornecedorDao fc = new FornecedorDao();
	//
	// public void selecionarFornecedor() {
	// List<Fornecedor> forn = new ArrayList<>();
	// forn = fc.listarFornecedor();
	// for (int i = 0; i < forn.size(); i++) {
	// System.out.println(forn.get(i));
	//
	// }
	// }

	// List<String> racas = new ArrayList<String>();
	//
	// racas.add("Cão");
	//
	// racas.add("Gato");
	//
	// racas.add("passaro");
	//
	// comboRaca.getItems().addAll(racas);
	//
	//
	// clientes = clienteNegocio.listarCliente();
	//
	// if(clientes != null) {
	//
	// clientes.forEach(cliente -> {
	//
	// ComboCliente.getItems().add(
	//
	// cliente.getNome() + " " + cliente.getSobrenome());
	//
	// }
	//
	// );
	//
	// }
	//
	// }

	FornecedorNegocio fn = new FornecedorNegocio();
	
	public void selecionarFornecedor() {
		List<Fornecedor> list = fn.listarFornecedorNome();
		cbFornecedores.getItems().clear();
 		for (int i = 0; i < list.size(); i++) {
 			System.out.println(list.get(i).toString());
 			cbFornecedores.getItems().addAll(list.get(i));
		}
		
	}

}