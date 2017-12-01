package controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.sql.rowset.Joinable;

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
import model.Produto;
import model.Vinculo;

public class ListaProdController implements Initializable {

	private VinculoDao vd = new VinculoDao();
	private List<Vinculo> vinculoList = vd.listarVinculoTabela();
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
	
	Produto pro = new Produto();

	public void populaView(List<Vinculo> vinculo) {
		Vinculo vincul = new Vinculo();
		if (!listaVinculo.isEmpty()) {
			listaVinculo.clear();
		}
		for (Vinculo vinc : vinculoList) {
			Vinculo v = new Vinculo(vinc.getIdProduto(), vinc.getMarca(), vinc.getValor());
			listaVinculo.add(v);

		}
		for (int i = 0; i < listaVinculo.size(); i++) {
			System.out.println(listaVinculo.get(i));
			colNome.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("idProduto"));
			colDesc.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("idProduto"));
		}
		colMarca.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("marca"));
		// colValue.setCellValueFactory(new PropertyValueFactory<Vinculo,
		// String>("valor"));

		listaVinculo = FXCollections.observableArrayList(vinculo);
		tbProd.setItems(listaVinculo);
	}

	@FXML
	void irParaSobre() {
	}

	@FXML
	void irParaLogin() {
	}

}
