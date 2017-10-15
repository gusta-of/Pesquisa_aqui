package controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

public class AdminController implements Serializable {

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
		colCpf.setCellValueFactory(new PropertyValueFactory<Admin, String>("telefone"));

		adminsView = FXCollections.observableArrayList(admin);
		tvTable.setItems(adminsView);

	}
	
	 public List<Admin> listarClientes(){
		 adminList = adminNegocio.listarAdmin();
	        return adminList;
	    }

}
