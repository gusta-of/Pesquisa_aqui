package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import dao.VinculoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Vinculo;

public class ListaProdController implements Initializable {

	private VinculoDao vd = new VinculoDao();
	private List<Vinculo> vinculoList = vd.listarVinculo();
	private ObservableList<Vinculo> listaVinculo = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Vinculo> vinculoList = vd.listarVinculo();
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

	@FXML
	private TableColumn<Vinculo, Double> colValue;

	@FXML
	private Button btAjuda;

	@FXML
	private Label lbMercado;

	@FXML
	private ImageView imgLogo;

	@FXML
	private TableColumn<Vinculo, String> colNome;

	@FXML
	private Button btnLogin;

	@FXML
	private TableColumn<Vinculo, String> colDesc;

	@FXML
	private AnchorPane acPane;

	@FXML
	private TableView<Vinculo> tbProd;

	@FXML
	private Label lbEnd;


Vinculo vincul = new Vinculo();
	public void populaView(List<Vinculo> vinculo) {

		if (!listaVinculo.isEmpty()) {
			listaVinculo.clear();
		}

		for (Vinculo vinc : vinculoList) {
			Vinculo v = new Vinculo(vinc.getIdProduto(), vinc.getMarca(), vinc.getValor());
			vinculoList.add(v);

		}

		colNome.setCellValueFactory(new PropertyValueFactory<Vinculo, String>(vincul.getIdProduto().getNomeProduto()));
		colDesc.setCellValueFactory(new PropertyValueFactory<Vinculo, String>(vincul.getIdProduto().getDescricao()));
		colMarca.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("marca"));
		colValue.setCellValueFactory(new PropertyValueFactory<Vinculo, Double>("valor"));

		listaVinculo = FXCollections.observableArrayList(vinculo);
		tbProd.setItems((ObservableList<Vinculo>) vinculoList);
		;
	}

	@FXML
	void irParaSobre() {
	}

	@FXML
	void irParaLogin() {
	}

}
