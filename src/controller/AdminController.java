package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Admin;
import negocio.AdminNegocio;
import negocio.LoginNegocio;

public class AdminController implements Initializable, Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private ImageView btAjuda, btSobre, imgLogo, imgInicio;

	@FXML
	private AnchorPane acPane;

	@FXML
	private Pane pnPane;

	@FXML
	private MenuItem miSobre;

	@FXML
	private Label ldTitulo, lbnome, lbsobrenome, lbuser, lbsenha, lbConfirm, lbCpf, lbemail, lbdata, lbMsg, lbMsgNeS,
			lbMsgCpf, lbMsgData, lbMsgEmail, lbMsgUser, lbMsgSenha, lbMsgConfSenha, lbAjuda, lbFornecedor;

	@FXML
	private TextField txnome, txCpf, txsobrenome, txuser, txsenha, txsConfirm, txemail;

	@FXML
	private Button btCancelar, btSalvar, btnLogin, btCadFornecedor, btCadProduto, btMain, idTestes;

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
	Admin admin = new Admin();
	LoginNegocio ln = new LoginNegocio();
	LoginController lc = new LoginController();

	// Esse m�todo � chamado ao inicializar a aplica��o, igual um construtor.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (lc.validarLogin() == true) {
			List<Admin> adminList = listarAdmin();
			populaView(adminList);
		}
	}

	private List<Admin> listarAdmin() {
		admins = adminNegocio.listarAdmin();
		return admins;
	}

	@FXML
	public void edit() throws IOException {

		Admin admin = new Admin();
		admin = (Admin) tvTable.getSelectionModel().getSelectedItem();
		this.admin = admin;
		setaValores(admin);
		btSalvar.setText("salvar");
		btCancelar.setText("Cancelar");
	}

	public void setarDadosAdmin() {

		admin.setNome(txnome.getText());
		admin.setSobrenome(txsobrenome.getText());
		admin.setUsuario(txuser.getText());
		admin.setEmail(txemail.getText());
		admin.setCpf(txCpf.getText());
		admin.setSenha(txsenha.getText());
		admin.setConfirmarSenha(txsConfirm.getText());
		admin.setDataNascimento(dpData.getValue());

	}

	private void setaValores(Admin admin) {

		txnome.setText(admin.getNome());
		txsobrenome.setText(admin.getSobrenome());
		txCpf.setText(admin.getCpf());
		dpData.setUserData(admin.getDataNascimento());
		txuser.setText(admin.getUsuario());
		txemail.setText(admin.getEmail());
		txsenha.setText(admin.getSenha());
		txsConfirm.setText(admin.getConfirmarSenha());

	}

	@FXML
	public void salvar() throws SQLException, ParseException {
		AdminNegocio adminN = new AdminNegocio();
		boolean validar = false;
		if (admin.getId() == 0) {
			setarDadosAdmin();
			validar = validarCampos(admin);
			if (validar == false) {
				validarCampos(admin);
				lbMsg.setVisible(validar);
			} else {
				if (adminN.salvar(admin).equals("salvo")) {
					admins.add(admin);
					populaView(admins);
					limparCampos();
					lbMsg.setVisible(false);
					// validarCampos(admin);
				} else
				// if(adminN.salvar(admin).equals("1")){
				// lbMsgData.setText("Precisa ter mais de 18 anos");
				// }
				// if(adminN.salvar(admin).equals("2")){
				// lbMsgCpf.setText("CPF Inválido");
				// }
				{

					lbMsg.setText(adminN.salvar(admin).toString());
					lbMsg.setVisible(true);

				}
			}
		} else {
			setarDadosAdmin();
			adminN.salvar(admin);
			listarAdmin();
			populaView(admins);
			limparCampos();
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
			lbMsgNeS.setText("Nome e/ou Sobrenome é/são obrigatório(s)!");
		}
		if (admin.getUsuario().equals("") || admin.getUsuario() == null) {
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
		colUser.setCellValueFactory(new PropertyValueFactory<Admin, String>("usuario"));

		adminsView = FXCollections.observableArrayList(admin);
		tvTable.setItems(adminsView);

	}

	public void logar() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/LoginAdm.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);

	}

	public void irParaFornecedor() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de fornecedor.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}

	public void irParaProduto() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de produtos.fxml");
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
}
