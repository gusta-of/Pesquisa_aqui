package controller;

import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class AdminController implements Initializable, Serializable {

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
	private Label ldTitulo, lbnome, lbsobrenome, lbuser, lbsenha, lbConfirm, lbCpf, lbemail, lbdata, lbMsg;

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

	List<Admin> admins = new ArrayList<Admin>();
	Main main = null;
	ObservableList<Admin> adminsView = null;
	AdminNegocio adminNegocio = new AdminNegocio();

	// Esse m�todo � chamado ao inicializar a aplica��o, igual um construtor.
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
	public void edit() {

		Admin admin = new Admin();
		admin = (Admin) tvTable.getSelectionModel().getSelectedItem();
		setarDadosAdmin(admin);
		btSalvar.setText("Editar");
		btCancelar.setText("Cancelar");
	}

	@FXML
	public void setarDadosAdmin(Admin admin) {

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
	public void salvar() throws SQLException, ParseException {
		String validacao = "falha";
		AdminNegocio adminN = new AdminNegocio();
		StringBuilder sb = new StringBuilder();
		boolean validar = false;
		Admin admin = new Admin();
		setarDadosAdmin(admin);
		admins.add(admin);
		validar = validarCampos(admin);
		if (validar != true) {
			sb.append(validacao);
		}
		if (sb.toString().equals("")) {
			validarCampos(admin);
		} else if (sb.toString().equals("falha")) {
			if (adminN.salvar(admin).equals("salvo")) {
				populaView(admins);
				limparCampos();
				lbMsg.setVisible(false);
				// validarCampos(admin);
			} else {
				lbMsg.setText(adminN.salvar(admin).toString());
				lbMsg.setVisible(true);
			}
		}

	}

	public void limparCampos() {
		txnome.setText("");
		txsobrenome.setText("");
		txCpf.setText("");
		dpData.setValue(null);
		txemail.setText("");
		txuser.setText("");
		txsenha.setText("");
		txsConfirm.setText("");
	}

	// =========
	// Cancelar
	// =========
	@FXML
	public void cancelar() {
		limparCampos();
	}

	// ====================
	// Validador Campos
	// ====================
	public boolean validarCampos(Admin admin) {
		StringBuilder inconsistencias = new StringBuilder();
		if (admin.getNome().equals("") || admin.getNome() == null) {
			inconsistencias.append("\n Campo Nome obrigat�rio");
		}
		if (admin.getSobrenome().equals("") || admin.getSobrenome() == null) {
			inconsistencias.append("\n Campo Sobrenome obrigat�rio");
		}
		if (admin.getUser().equals("") || admin.getUser() == null) {
			inconsistencias.append("\n Campo Usuario obrigat�rio");
		}
		if (admin.getEmail().equals("") || admin.getEmail() == null) {
			inconsistencias.append("\n Campo Email obrigat�rio");
		}
		if (admin.getCpf().equals("") || admin.getCpf() == null) {
			inconsistencias.append("\n Campo CPF obrigat�rio");
		}
		if (admin.getSenha().equals("") || admin.getSenha() == null) {
			inconsistencias.append("\n Campo Senha obrigat�rio");
		}
		if (admin.getConfirmarSenha().equals("") || admin.getConfirmarSenha() == null) {
			inconsistencias.append("\n Campo Confirmar Senha obrigat�rio");
		}
		if (admin.getDataNascimento() == null) {
			inconsistencias.append("\n Campo Data obrigat�rio");
		}

		System.out.println(inconsistencias.toString());
		return true;
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
