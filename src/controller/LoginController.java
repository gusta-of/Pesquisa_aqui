package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private AnchorPane apnPaneLogin, apnBarra, acBarra;

	@FXML
	private TextField txtUser, txtSenha;

	@FXML
	private Button btLoguin, btInicio, btAjuda, btSobre;

	@FXML
	private ImageView imgBusto, imgCadeado, imgPc, imgLoad2, imgLogo;

	@FXML
	private Label lbMsgUser, lbMsgSenha;

//	List<Admin> admins = new ArrayList<Admin>();
	LoginNegocio loginNegocio = new LoginNegocio();
	Admin admin = new Admin();

//	public List<Admin> listarAdmin() {
//		List<Admin> admins = new ArrayList<>();
//		admins = loginNegocio.listarAdmin();
//		return admins;
//	}

	public boolean logar() throws IOException {
		List<Admin> admins = new ArrayList<>();
		admins = loginNegocio.listarAdmin();
		for (int i = 0; i < admins.size(); i++) {
			if (admins.get(i).getUsuario().equals(txtUser.getText())) {
				if (admins.get(i).getSenha().equals(txtSenha.getText())) {
					URL arquivoFxml;
					arquivoFxml = getClass().getResource("/visao/Main adm.fxml");
					Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
					apnPaneLogin.getChildren().clear();
					apnPaneLogin.getChildren().add(fxmlParent);
					imgLoad2.setVisible(true);
				}else {
					lbMsgSenha.setText("Senha Incorreta!");
					lbMsgSenha.setVisible(true);
				}
			}else {
				lbMsgUser.setText("Usuário Incorreto!");
				lbMsgUser.setVisible(true);
			}
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
	
	public void irParaInicio() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Inicio.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		apnPaneLogin.getChildren().clear();
		apnPaneLogin.getChildren().add(fxmlParent);
	}
	
	public void irParaSobre() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Sobre.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		apnPaneLogin.getChildren().clear();
		apnPaneLogin.getChildren().add(fxmlParent);
	}
	
	public void irParaAjuda() {
		
	}
	
}
