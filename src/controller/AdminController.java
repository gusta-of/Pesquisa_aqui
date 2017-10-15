package controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import model.Admin;
import negocio.AdminNegocio;

public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Pane pnPane;

	@FXML
	private MenuBar mbMenuBar;

	@FXML
	private Menu mPesquisa, mAjuda;

	@FXML
	private MenuItem miSobre;

	@FXML
	private Label ldTitulo, lbnome, lbsobrenome, lbuser, lbsenha, lbConfirm, lbCpf, lbemail, lbdata;

	@FXML
	private TextField txnome, txsobrenome, txuser, txsenha, txsConfirm, txCpf, txemail;

	@FXML
	private Button btCancelar, btSalvar;

	@FXML
	private TableView<Admin> tvTable;

	@FXML
	private TableColumn<Admin, String> colNome;

	@FXML
	private TableColumn<Admin, String> colSobre;

	@FXML
	private TableColumn<Admin, String> colEmail;

	@FXML
	private TableColumn<Admin, String> colCpf;

	@FXML
	private TableColumn<Admin, String> colUser;

	@FXML
	private DatePicker dpData;

	private Admin admin;

	public void setarDadosAdmin() {

		this.admin = new Admin();
		admin.setNome(txnome.getText());
		admin.setSobrenome(txsobrenome.getText());
		admin.setUser(txuser.getText());
		admin.setEmail(txemail.getText());
		admin.setCpf(txCpf.getText());
		admin.setSenha(txsenha.getText());
		admin.setConfirmarSenha(txsConfirm.getText());
		admin.setDataNascimento(dpData.getValue());

	}

	@FXML
	public void salvar() throws ParseException {
		setarDadosAdmin();
		AdminNegocio adminNegocio = new AdminNegocio();
		String salvo = adminNegocio.salvar(this.admin);
		System.out.println(salvo);
	}
	
	ObservableList<Admin> adminView = null;
	
	public void populaView(List<Admin> admin) {

		colNome.setCellValueFactory(new PropertyValueFactory<Admin, String>("nome"));
		colSobre.setCellValueFactory(new PropertyValueFactory<Admin, String>("sobrenome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Admin, String>("telefone"));

		adminView = FXCollections.observableArrayList(admin);
		tvTable.setItems(adminView);

	}

	public static boolean validaCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

}
