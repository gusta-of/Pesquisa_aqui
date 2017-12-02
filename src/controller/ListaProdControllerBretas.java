package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.VinculoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Vinculo;

public class ListaProdControllerBretas implements Initializable {

	private VinculoDao vd = new VinculoDao();
	private List<Vinculo> vinculoList = vd.listarVinculoTabelaBretas();
	private ObservableList<Vinculo> listaVinculo = FXCollections.observableArrayList();

	Vinculo v = new Vinculo();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		populaView(vinculoList);

	}

	@FXML
	private Label lbTotal;

	@FXML
	private TableColumn<Vinculo, String> colMarca;

	@FXML
	private ImageView btAjuda2;

	@FXML
	private ImageView btAjuda1;

	// @FXML
	// private TableColumn<Vinculo, String> colValue;

	@FXML
	private Button btAjuda, btInicio;

	@FXML
	private Label lbMercado;

	@FXML
	private ImageView imgLogo;

	@FXML
	private TableColumn<Vinculo, String> colNome;

	@FXML
	private Button btnLogin;

	@FXML
	private TableColumn<Vinculo, Double> colvalor;

	@FXML
	private AnchorPane acPane;

	@FXML
	private TableView<Vinculo> tbProd;

	@FXML
	private TableView<Vinculo> tbProd1;

	@FXML
	private Label lbEnd;

	public void populaView(List<Vinculo> vinculo) {
		if (!listaVinculo.isEmpty()) {
			listaVinculo.clear();
		}
		for (Vinculo vinc : vinculoList) {
			Vinculo v = new Vinculo(vinc.getIdProduto(), vinc.getMarca(), vinc.getValor());
			listaVinculo.add(v);

		}
		for (int i = 0; i < listaVinculo.size(); i++) {
			System.out.println(listaVinculo.get(i).getIdProduto().getNomeProduto());
			colNome.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("idProduto"));
		}
		
		colMarca.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("marca"));
		colvalor.setCellValueFactory(new PropertyValueFactory<Vinculo, Double>("valor"));

		listaVinculo = FXCollections.observableArrayList(vinculo);
		tbProd.setItems(listaVinculo);

	}
	
	public void IrParaInicio() throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource("/visao/Inicio.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPane.getChildren().clear();
		acPane.getChildren().add(fxmlParent);
	}
	
	@FXML
	void irParaSobre() {
	}

	@FXML
	void irParaLogin() {
	}

}
