package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Admin;
import negocio.LoginNegocio;

public class LoginController {

	@FXML
	private AnchorPane apnPaneLogin, apnBarra;

	@FXML
	private TextField txtUser, txtSenha;

	@FXML
	private Button btLoguin;

	@FXML
	private ImageView imgLogo, imgBusto, imgCadeado, imgPc, imgLoad2;

	@FXML
	private Label lblDados;

	List<Admin> admins = new ArrayList<Admin>();
	LoginNegocio loginNegocio = new LoginNegocio();
	Admin admin = new Admin();

	public List<Admin> listarAdmin() {
		List<Admin> admins = new ArrayList<>();
		admins = loginNegocio.listarAdmin();
		return admins;
	}

	public boolean logar() throws IOException {
		String user = txtUser.getText();
		String senha = txtUser.getText();
		try {
			LoginDao lgDao = new LoginDao();
			lgDao.login(admin);
		} catch (Exception e) {
			System.out.println("Algo deu Errado ao logar!" + e.getMessage());
		}
		return true;
	}

	public void pegaValores() {
		txtUser.getText();
		txtSenha.getText();
	}

	public boolean validarLogin() {

		return true;
	}

	public void cadastrar() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Cadastro de Administrador.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		apnPaneLogin.getChildren().clear();
		apnPaneLogin.getChildren().add(fxmlParent);
		imgLoad2.setVisible(true);
	}

	public void limpar() {
		txtUser.setText("");
		txtSenha.setText("");
	}
}
