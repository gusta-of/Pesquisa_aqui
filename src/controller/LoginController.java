package controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

	public boolean logar() throws IOException {
		
		String login = "";
		String senha = "";
		login = txtUser.getText();
		senha = txtSenha.getText();
		if (login.equals("usuario") && senha.equals("123456")) {
			URL arquivoFxml;
			arquivoFxml = getClass().getResource("/visao/Cadastro de fornecedor.fxml");
			Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
			apnPaneLogin.getChildren().clear();
			apnPaneLogin.getChildren().add(fxmlParent);
			imgLoad2.setVisible(true);
		} else {
			lblDados.setText("Dados incoretos!");
			lblDados.setVisible(true);
		}
		
		return true;
	}
	
	public boolean validarLog() throws IOException {
		
//		boolean validar = false;
//		if(this.logar() != true) {
//			System.out.println("Algo errado na validação de logar!");
//			validar = false;
//		}else {
//			validar = true;
//			System.out.println("Deu certo!*******");
//		}
//		return validar;
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
