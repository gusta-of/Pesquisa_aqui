package controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SobreController{
	
	@FXML
	private AnchorPane acPane, acPane2;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private ImageView imgLogo;
	
	
	public void irParaAjuda() throws IOException{
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Ajuda.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
//	public void irParaLogin() throws IOException{
//		URL arquivoFxml;
//		arquivoFxml = getClass().getResource("/visao/LoginAdm.fxml");
//		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
//		acPane.getChildren().clear();
//		acPane.getChildren().add(fxmlParent);
//	}
	
	public void irParaInicio() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Inicio.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
}
