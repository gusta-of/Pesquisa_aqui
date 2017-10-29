package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable{
	
	@FXML
	private AnchorPane apnPaneLogin, apnBarra;
	
	@FXML	
	private TextField txtUser, txtSenha;
	
	@FXML 
	private Button btLoguin;
	
	@FXML 
	private ImageView imgLogo, imgBusto, imgCadeado, imgPc, imgLoad2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
