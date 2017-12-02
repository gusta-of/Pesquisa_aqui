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
	private List<Vinculo> vinculoList = vd.listarVinculoTabela();
	private List<Vinculo> vinculoList1 = vd.listarVinculoTabela1();
	private ObservableList<Vinculo> listaVinculo = FXCollections.observableArrayList();
	private ObservableList<Vinculo> listaVinculo1 = FXCollections.observableArrayList();
	Vinculo v = new Vinculo();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		populaView(vinculoList);
		populaView1(vinculoList1);

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
			colNome.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("idProduto"));
		}

		// for(int i = 0; i < listaVinculo1.size(); i++) {
		// System.out.println(listaVinculo1.get(i));
		// colDesc.setCellValueFactory(new PropertyValueFactory<Vinculo,
		// String>("idProduto"));
		// }

		listaVinculo = FXCollections.observableArrayList(vinculo);
		tbProd.setItems(listaVinculo);

		// listaVinculo1 = FXCollections.observableArrayList(vinculo);
		// tbProd1.setItems(listaVinculo1);
	}

	public void populaView1(List<Vinculo> vinculo1) {
		if (!listaVinculo1.isEmpty()) {
			listaVinculo1.clear();
		}

		for (Vinculo vinc1 : vinculoList1) {
			Vinculo v1 = new Vinculo(vinc1.getIdProduto(), vinc1.getMarca(), vinc1.getValor());
			listaVinculo1.add(v1);

			for (int i = 0; i < listaVinculo1.size(); i++) {
				colDesc.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("idProduto"));
			}

			listaVinculo1 = FXCollections.observableArrayList(vinculo1);
			tbProd1.setItems(listaVinculo1);
		}

	}

	@FXML
	void irParaSobre() {
	}

	@FXML
	void irParaLogin() {
	}

}
