package controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Fornecedor;
import negocio.FornecedorNegocio;

public class FornecedorController implements Initializable, Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Pane pnPaneF;

	@FXML
	private MenuBar mbMenuBar;

	@FXML
	private Menu mPesquisar, mSobre, mAjuda;

	@FXML
	private MenuItem miSobre;

	@FXML
	private Label lbTitulo, lbNome, lbEndereco, lbMsgNome, lbMsgEndereco;

	@FXML
	private TextField txNome, txEndereco;

	@FXML
	private Button btSalvar, btCancelar;

	@FXML
	private TableView<Fornecedor> tvTable;

	@FXML
	private TableColumn<Fornecedor, String> colNome;

	@FXML
	private TableColumn<Fornecedor, String> colEndereco;

	@FXML
	private TableColumn<Fornecedor, Integer> colId;

	FornecedorNegocio fornecedorN = new FornecedorNegocio();

	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	// private Main main = null;
	ObservableList<Fornecedor> fornecedoresView = null;
	FornecedorNegocio fornecedorNegocio = new FornecedorNegocio();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Fornecedor> fornecedorList = listarFornecedor();
		populaView(fornecedorList);
	}

	private List<Fornecedor> listarFornecedor() {
		fornecedores = fornecedorNegocio.listarFornecedor();
		return fornecedores;
	}

	@FXML
	public void salvar() {
		Fornecedor fornecedor = new Fornecedor();
		setarDadosFornecedor(fornecedor);
		if (fornecedor.getId() == 0) {

			boolean validar = valiarCampos(fornecedor);
			if (validar == false) {
				valiarCampos(fornecedor);
			} else if (fornecedorNegocio.salvar(fornecedor) == true) {
				
				listarFornecedor();
				populaView(fornecedores);
			}

		} else {
			setarDadosFornecedor(fornecedor);
			fornecedorN.salvar(fornecedor);
			populaView(fornecedores);
			limparCampos();
		}
	}

	public void limparCampos() {
		lbNome.setText("");
		lbEndereco.setText("");
	}

	public void setarDadosFornecedor(Fornecedor fornecedor) {

		fornecedor.setNome(txNome.getText());
		fornecedor.setEndereco(txEndereco.getText());

	}

	private void populaView(List<Fornecedor> fornecedor) {

		// colId.setCellValueFactory(new PropertyValueFactory<Fornecedor,
		// Integer>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));

		fornecedoresView = FXCollections.observableArrayList(fornecedor);
		tvTable.setItems(fornecedoresView);
	}

	private boolean valiarCampos(Fornecedor fornecedor) {
		StringBuilder inconsistencias = new StringBuilder();

		if (fornecedor.getNome().equals("") || fornecedor.getNome() == null) {
			inconsistencias.append("Campo obrigatório!");
		}
		if (fornecedor.getEndereco().equals("") || fornecedor.getEndereco() == null) {
			inconsistencias.append("Campo obrigatório!");
		}
		System.out.println(inconsistencias.toString());
		lbMsgNome.setVisible(false);
		lbMsgEndereco.setVisible(false);
		return true;
	}

	@FXML
	public void edit() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor = (Fornecedor) tvTable.getSelectionModel().getSelectedItem();

		txNome.setText(fornecedor.getNome());
		txEndereco.setText(fornecedor.getEndereco());

		btSalvar.setText("Editar");
	}

}
