package controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class InicioController {
	
	@FXML
	private AnchorPane acPane;
	
	@FXML
	private ImageView imgLogo, btAjuda, btSobre, imgInicio;
	
	@FXML
	private Button btnMercado1, btnLogin;
	
	
	public void irParaLogin() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/LoginAdm.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
}
