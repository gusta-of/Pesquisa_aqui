package controller;

import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
	private TextField txnome, txsobrenome, txuser, txsenha, txsConfirm, txCpf, txemail;

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

	List<Admin> adminList = new ArrayList<Admin>();
	Main main = null;
	ObservableList<Admin> adminsView = null;
	AdminNegocio adminNegocio = new AdminNegocio();

	// Esse método é chamado ao inicializar a aplicação, igual um construtor.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Admin> adminList = listarAdmin();
		populaView(adminList);
	}

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
	
	private List<Admin> listarAdmin() {
		//Aqui eu populo uma lista de objetos admin
		List<Admin> list = new ArrayList<Admin>();
		Admin admin = new Admin();
		admin.setNome(colNome.toString());
		admin.setSobrenome(colSobre.getStyle());
		admin.setEmail(colEmail.getStyle());
		admin.setCpf(colCpf.getStyle());
		admin.setUser(colUser.getStyle());
		list.add(admin);
//		admin.setNome("teste");
	//	admin.setSobrenome("testeSobrenome");
//		admin.setCpf("11111111111");
//		admin.setEmail("teste@teste.com");
//		admin.setUser("Gustavo");
//		list.add(admin);
//		Admin admin2 = new Admin();
//		admin2.setNome("teste2");
//		admin2.setSobrenome("testeSobrenome2");
//		admin2.setCpf("22222222222");
//		admin2.setEmail("teste2@teste2.com");
//		admin2.setUser("Gustavo2");
//		list.add(admin2);
		return list;
	}

	@FXML
	public void salvar() throws ParseException {
		setarDadosAdmin();
		AdminNegocio adminNegocio = new AdminNegocio();
		String salvo = adminNegocio.salvar(this.admin);
		System.out.println(salvo);
	}

	ObservableList<Admin> adminView = null;

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
