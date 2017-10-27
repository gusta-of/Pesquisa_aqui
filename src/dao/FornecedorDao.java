package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import daoUtil.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorDao {

	private PreparedStatement stmt;

	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	private String sqlSalvar = "INSERT INTO fornecedores" + "(nome, endereco) VALUES (?, ?)";

	private String sqlEditar = "UPDATE fornecedores SET nome = ?, endereco = ? WHERE id = ?";

	private String sqlDelete = "DELETE fornecedores WHERER id = ?";

	public FornecedorDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();

	}

	public List<Fornecedor> listarFornecedor() {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		ResultSet res = null;
		try {
			if (this.con != null) {
				stm = con.createStatement();
				res = stm.executeQuery("SELECT * FROM fornecedores");
				while (res.next()) {
					Fornecedor fornecedor = new Fornecedor();
					fornecedor.setNome(res.getString("nome"));
					fornecedor.setEndereco(res.getString("endereco"));

					list.add(fornecedor);
				}
			}

			return list;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar: " + e.getMessage());
			return new ArrayList<Fornecedor>();
		}
	}

	public boolean salvar(Fornecedor fornecedor) {
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getEndereco());

			stmt.executeQuery();
			con.commit();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao inserir: " + e.getMessage());
			return false;
		}

	}

	public boolean editar(Fornecedor fornecedor) {
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlEditar);

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getEndereco());
			stmt.setInt(3, fornecedor.getId());

			stmt.executeUpdate();
			con.commit();
			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar: " + e.getMessage());
			return false;
		}
	}

	public boolean deletar(Fornecedor fornecedor) {
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlDelete);

			stmt.setInt(1, fornecedor.getId());

			stmt.execute();
			con.commit();
			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao deletar: " + e.getMessage());
			return false;
		}

	}
}
