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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Admin;
import negocio.AdminNegocio;

public class MainAdminController implements Initializable, Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane acPane;

	@FXML
	private Button btCadProduto, btCadAdmin, btCadMercado;

	@FXML
	private ImageView imgLogo;

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

	LoginController lc = new LoginController();
	ObservableList<Admin> adminsView = null;
	List<Admin> admins = new ArrayList<Admin>();
	AdminNegocio adminNegocio = new AdminNegocio();

	public void initialize(URL location, ResourceBundle resources) {

			List<Admin> adminList = listarAdmin();
			populaView(adminList);
	}

	private List<Admin> listarAdmin() {
		admins = adminNegocio.listarAdmin();
		return admins;
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

	public void irParaAdmin() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de administrador.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
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

}
