package controller;

import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Fornecedor;
import negocio.FornecedorNegocio;

public class FornecedorController implements Initializable, Serializable {
   
   private static final long serialVersionUID = 1L;
   
   @FXML
   private Pane pnPaneF;
   
   @FXML
   private MenuBar mbMenuBar;
   
   @FXML
   private Menu mPesquisar, mSobre, mAjuda;
   
   @FXML
   private MenuItem miSobre;
   
   @FXML
   private Label lbTitulo, lbNome, lbEndereco, lbMsgNome, lbMsgEndereco;
   
   @FXML
   private TextField txNome, txEndereco;
   
   @FXML
   private Button btSalvar, btCancelar;
   
   @FXML
   private TableView<Fornecedor> tvTable;
   
   @FXML
   private TableColumn<Fornecedor, String> colNome;
   
   @FXML
   private TableColumn<Fornecedor, String> colEndereco;
   
   @FXML
   private TableColumn<Fornecedor, Integer> colId;
   
   FornecedorNegocio fornecedorN = new FornecedorNegocio();
   Fornecedor fornecedor = new Fornecedor();
   private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
   // private Main main = null;
   ObservableList<Fornecedor> fornecedoresView = null;
   FornecedorNegocio fornecedorNegocio = new FornecedorNegocio();
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      List<Fornecedor> fornecedorList = listarFornecedor();
      populaView(fornecedorList);
   }
   
   private List<Fornecedor> listarFornecedor() {
      fornecedores = fornecedorNegocio.listarFornecedor();
      return fornecedores;
   }
   
   @FXML
   public void salvar() throws SQLException {
      boolean validar = false;
      if (fornecedor.getId() == 0) {
         setarDadosFornecedor();
         fornecedores.add(fornecedor);
         validar = validarCampos(fornecedor);
         if (validar == false) {
            validarCampos(fornecedor);
         } else {
            if (fornecedorN.salvar(fornecedor) == true) {
               populaView(fornecedores);
               limparCampos();
            } else {
               validarCampos(fornecedor);
               System.out.println("Algo errado em validar Campos!");
            }
         }
      }else {
         setarDadosFornecedor();
         fornecedorN.salvar(fornecedor);
         listarFornecedor();
         populaView(fornecedores);
         limparCampos();
      }
   }
   
   public void limparCampos() {
      txNome.setText("");
      txEndereco.setText("");
   }
   
   public void setarDadosFornecedor() {
      
      txNome.setText(fornecedor.getNome());
      txEndereco.setText(fornecedor.getEndereco());
      
   }
   
   private void populaView(List<Fornecedor> fornecedor) {
      
      colNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
      colEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
      
      fornecedoresView = FXCollections.observableArrayList(fornecedor);
      tvTable.setItems(fornecedoresView);
   }
   
   private boolean validarCampos(Fornecedor fornecedor) {
      StringBuilder inconsistencias = new StringBuilder();
      if (fornecedor.getNome().equals("") || fornecedor.getNome() == null) {
         inconsistencias.append("Campo Obrigatório!");
      }
      if(fornecedor.getEndereco().equals("") || fornecedor.getEndereco() == null) {
         inconsistencias.append("Campo Obrigatório!");
      }
      System.out.println(inconsistencias.toString());
      return true;
   }
   
   @FXML
   public void edit() {
      Fornecedor fornecedor = new Fornecedor();
      fornecedor = (Fornecedor) tvTable.getSelectionModel().getSelectedItem();
      
      txNome.setText(fornecedor.getNome());
      txEndereco.setText(fornecedor.getEndereco());
      
      btSalvar.setText("Editar");
   }
   
}
