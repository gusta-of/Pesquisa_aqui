package controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class InicioController {
	
	@FXML
	private AnchorPane acPane;
	
	@FXML
	private Pane P;
	
	@FXML
	private ImageView imgLogo, imgInicio, btAjuda1;
	
	@FXML
	private Button btnMercado1, btAjuda, btnLogin, btSobre, btMakro, btAssai, btBretas, btBarao;
	
	
	public void irParaListaProdutoMakro() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Produtos.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaListaProdutoAssai() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Produtos Assai.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaListaProdutoBretas() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Produtos Bretas.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaListaProdutoBarao() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Produtos Barao.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	
	public void irParaLogin() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/LoginAdm.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaSobre() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Sobre.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	public void irParaAjuda() throws IOException{
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Ajuda.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
}
