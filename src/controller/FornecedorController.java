package controller;

import java.util.List;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
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
import javax.swing.JOptionPane;

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
	
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private Main main = null;
	private ObservableList<Fornecedor> fornecedoresView = null;
	FornecedorNegocio fornecedorNegocio = new FornecedorNegocio();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populaView();
	}
	
	@FXML
	public void salvar() {
		Fornecedor fornecedor = new Fornecedor();
		if(this.valiarCampos(fornecedor)) {
			if(this.fornecedorNegocio.salvar(fornecedor)) {
				this.populaView();
			}else {
				JOptionPane.showMessageDialog(null, "Não foi possível salvar!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
	
	private void populaView() {
		fornecedores = fornecedorNegocio.listarFornecedores();
		
		colId.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
		
		fornecedoresView = FXCollections.observableArrayList(fornecedores);
		tvTable.setItems(fornecedoresView);
	}

	
	private boolean valiarCampos(Fornecedor fornecedor) {
		if(fornecedor.getNome().equals("") || fornecedor.getNome() == null) {
			lbMsgNome.setText("Campo obrigatório");
			lbMsgNome.setVisible(true);
			return false;
		}
		if(fornecedor.getEndereco().equals("") || fornecedor.getEndereco() == null) {
			lbMsgEndereco.setText("Campo obrigatório");
			lbMsgEndereco.setVisible(true);
			return false;
		}
		lbMsgNome.setVisible(false);
		lbMsgEndereco.setVisible(false);
		return true;
	}
	
	private void setarDados(Fornecedor fornecedor) {
		fornecedor.setNome(txNome.getText());
		fornecedor.setEndereco(txEndereco.getText());
		
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
