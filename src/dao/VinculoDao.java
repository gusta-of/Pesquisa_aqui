package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoUtil.ConnectionFactory;
import model.Fornecedor;
import model.Produto;
import model.Vinculo;

public class VinculoDao {
	private PreparedStatement stmt;

	private Connection con;
	private Statement stm;
	ConnectionFactory connection = null;

	public VinculoDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlSalvar = "INSERT INTO vinculo (idProduto, idFornecedor, valor, marca) VALUES(?,?,?,?)";

	public List<Vinculo> listarVinculoTabela() {
		List<Vinculo> list = new ArrayList<>();
		ResultSet res = null;
		try {
			if (con != null) {
				stm = con.createStatement();
				res = stm.executeQuery("SELECT * FROM vinculo WHERE idFornecedor = ?");
				while (res.next()) {
					Vinculo vinculo = new Vinculo();

					vinculo.setId(res.getInt("id"));
					vinculo.setIdProduto(res.getObject("idProduto", Produto.class));
					vinculo.setIdFornecedor(res.getObject("idFornecedor", Fornecedor.class));
					vinculo.setMarca(res.getString("marca"));
					vinculo.setValor(res.getDouble("valor"));
					list.add(vinculo);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar" + e.getMessage());
		}

		return list;

	}
	
	public List<Vinculo> listarVinculo() {
		List<Vinculo> list = new ArrayList<>();
		ResultSet res = null;
		try {
			if (con != null) {
				stm = con.createStatement();
				res = stm.executeQuery("SELECT * FROM vinculo");
				while (res.next()) {
					Vinculo vinculo = new Vinculo();

					vinculo.setId(res.getInt("id"));
					vinculo.setIdProduto(res.getObject("idProduto", Produto.class));
					vinculo.setIdFornecedor(res.getObject("idFornecedor", Fornecedor.class));
					vinculo.setMarca(res.getString("marca"));
					vinculo.setValor(res.getDouble("valor"));
					list.add(vinculo);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar" + e.getMessage());
		}

		return list;

	}

	public String salvar(Vinculo vinculo) {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);
			stmt.setInt(1, vinculo.getIdProduto().getId());
			stmt.setInt(2, vinculo.getIdFornecedor().getId());
			stmt.setDouble(3, vinculo.getValor());
			stmt.setString(4, vinculo.getMarca());

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro ao salvar" + e.getMessage());
		}
		return salvo;
	}
}
