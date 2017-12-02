package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.AdminDao;
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

	/*
	 * Aqui eu crio um objeto instancia de admin dao!
	 */
	private AdminDao adminDao = new AdminDao();

	/*
	 * Aqui eu crio uma lista de admin q busca todos admins salvos no banco!
	 */
	private List<Admin> adminList = adminDao.listarAdmin();

	/*
	 * Aqui eu crio um observableList..!
	 */
	private ObservableList<Admin> listAdmin = FXCollections.observableArrayList();

	@FXML
	private AnchorPane acPane;

	@FXML
	private Button btCadProduto, btCadAdmin, btCadMercado, btAjudaAdm, btSairAdm, btAjudaS, btInicioS, btVinculo;

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
	// ObservableList<Admin> adminsView = null;
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
	
	public void irParaVinculo() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Vinculo.fxml");
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

	public void sairParaLogin() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/LoginAdm.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	/*
	 * Esse metodo pega um objeto que guarda a lista de admin que vem do banco,
	 * entra dentro do forEach popula todos os campos com os admins cadastrados no
	 * banco!
	 */

	public void populaView(List<Admin> admin) {

		if (!listAdmin.isEmpty()) {
			listAdmin.clear();
		}

		for (Admin admin1 : adminList) {

			Admin a = new Admin(admin1.getNome(), admin1.getSobrenome(), admin1.getDataNascimento(), admin1.getEmail(),
					admin1.getCpf(), admin1.getUsuario());
			listAdmin.add(a);
		}

		colNome.setCellValueFactory(new PropertyValueFactory<Admin, String>("nome"));
		colSobre.setCellValueFactory(new PropertyValueFactory<Admin, String>("sobrenome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Admin, String>("cpf"));
		colUser.setCellValueFactory(new PropertyValueFactory<Admin, String>("usuario"));

		listAdmin = FXCollections.observableArrayList(admin);
		tvTable.setItems(listAdmin);

	}

}
