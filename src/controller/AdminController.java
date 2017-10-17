package controller;

import java.io.Serializable;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Admin;
import negocio.AdminNegocio;

public class AdminController implements  Initializable, Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Pane pnPane;

	@FXML
	private MenuBar mbMenuBar;

	@FXML
	private Menu mPesquisa, mAjuda;

	@FXML
	private MenuItem miSobre;

	@FXML
	private Label ldTitulo, lbnome, lbsobrenome, lbuser, lbsenha, lbConfirm, lbCpf, lbemail, lbdata;

	@FXML
	private TextField txnome, txCpf, txsobrenome, txuser, txsenha, txsConfirm, txemail;

	@FXML
	private Button btCancelar, btSalvar;

	@FXML
	private TableView<Admin> tvTable;

	@FXML
	private TableColumn<Admin, String> colNome;

	@FXML
	private TableColumn<Admin, String> colSobre;

	@FXML
	private TableColumn<Admin, String> colEmail;

	@FXML
	private TableColumn<Admin, String> colCpf;

	@FXML
	private TableColumn<Admin, String> colUser;

	@FXML
	private DatePicker dpData;

	private Admin admin;

	List<Admin> admins = new ArrayList<Admin>();
	Main main = null;
	ObservableList<Admin> adminsView = null;
	AdminNegocio adminNegocio = new AdminNegocio();

	// Esse método é chamado ao inicializar a aplicação, igual um construtor.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Admin> adminList = listarAdmin();
		populaView(adminList);
	}

	private List<Admin> listarAdmin() {
		admins = adminNegocio.listarAdmin();
		return admins;
	}

	@FXML
	public void setarDadosAdmin() {

		this.admin = new Admin();
		admin.setNome(txnome.getText());
		admin.setSobrenome(txsobrenome.getText());
		admin.setUser(txuser.getText());
		admin.setEmail(txemail.getText());
		admin.setCpf(txCpf.getText());
		admin.setSenha(txsenha.getText());
		admin.setConfirmarSenha(txsConfirm.getText());
		admin.setDataNascimento(dpData.getValue());

	}
		
	 

	@FXML
	public void salvar() throws SQLException {
		String validar;
		boolean validacao = true;
		admin = new Admin();
		validacao = validarCampos();
	}

	// ====================
		// Validador Campos
		// ====================
		public boolean validarCampos() {
			StringBuilder inconsistencias = new StringBuilder();
			if(admin.getNome().equals("") || admin.getNome() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getSobrenome().equals("") || admin.getSobrenome() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getUser().equals("") || admin.getUser() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getEmail().equals("") || admin.getEmail() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getCpf().equals("") || admin.getCpf() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getSenha().equals("") || admin.getSenha() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			if(admin.getConfirmarSenha().equals("") || admin.getConfirmarSenha() == null) {
				inconsistencias.append("\n Campo obrigatório");
			}
			System.out.println(inconsistencias.toString());
			return  true;
		}

	public void populaView(List<Admin> admin) {

		colNome.setCellValueFactory(new PropertyValueFactory<Admin, String>("nome"));
		colSobre.setCellValueFactory(new PropertyValueFactory<Admin, String>("sobrenome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Admin, String>("cpf"));
		colUser.setCellValueFactory(new PropertyValueFactory<Admin, String>("user"));
		
		adminsView = FXCollections.observableArrayList(admin);
		tvTable.setItems(adminsView);

	}
	

}
