package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoUtil.ConnectionFactory;
import model.Produto;

public class ProdutoDao {

	private PreparedStatement stmt;

	private Connection con;
	private Statement stm;
	ConnectionFactory connection = null;

	public ProdutoDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlSalvar = "INSERT INTO produto(nomeProduto, codigo, descricao) VALUES(?,?,?)";

	String sqlEditar = "UPDATE produto SET codigo = ?, nomeProduto = ?, descricao = ?, WHERE id = ?";

	String sqlDeletar = "DELETE FROM produto WHERE id = ?";

	public List<Produto> listarProduto() {
		List<Produto> list = new ArrayList<Produto>();
		ResultSet res = null;
		try {
			if (con != null) {
				stm = con.createStatement();
				res = stm.executeQuery("SELECT * FROM produto");
				while (res.next()) {
					Produto produto = new Produto();

					produto.setId(res.getInt("id"));
					// produto.setIdFornecedor(res.getObject("idFornecedor", Fornecedor.class));
					produto.setNomeProduto(res.getString("nomeProduto"));
					produto.setCodigo(res.getInt("codigo"));
					produto.setDescricao(res.getString("descricao"));
					list.add(produto);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta 1:" + e.getMessage());
		}
		return list;
	}

	public String salvar(Produto produto) {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);

			// stmt.setInt(1, produto.getIdFornecedor().getId());
			stmt.setString(1, produto.getNomeProduto());
			stmt.setInt(2, produto.getCodigo());
			stmt.setString(3, produto.getDescricao());

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar(Salvar DAO)" + e.getMessage());
		}
		return salvo;
	}

	public String editar(Produto produto) {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlEditar);

			stmt.setInt(1, produto.getCodigo());
			stmt.setString(2, produto.getNomeProduto());
			stmt.setString(3, produto.getDescricao());

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar" + e.getMessage());
			salvo = e.getMessage();
		}
		return salvo;
	}

}
