package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoUtil.ConnectionFactory;
import model.Admin;

public class LoginDao {

	private PreparedStatement stmt;
	private Connection con;
	// private Statement stm;

	Connection connection = null;

	public LoginDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlLogar = "SELECT * FROM admin WHERE usuario = usuario AND senha = senha";

	// public boolean login(Admin admin) throws SQLException {
	// String sql = "SELECT usuario, senha FROM admin WHERE usuario = ? AND senha =
	// ?";
	//
	// con.setAutoCommit(false);
	// stmt = con.prepareStatement(sql);
	//
	// stmt.setString(1, admin.getUsuario());
	// stmt.setString(2, admin.getSenha());
	//
	// stmt.executeUpdate();
	// con.commit();
	//
	//
	// while(res.next()) {
	// Admin user = new Admin();
	// user.setUsuario(res.getString("usuario"));
	// user.setSenha(res.getString("senha"));
	//
	//
	// }
	//
	// return true;
	// }

	public List<Admin> listarAdmin() {
		ResultSet res = null;
		List<Admin> lista = new ArrayList<Admin>();
		String sqlListar = "SELECT * FROM admin";
		try {
			stmt = con.prepareStatement(sqlListar);
			res = stmt.executeQuery();
			while (res.next()) {
				Admin user = new Admin();
				user.setId(res.getInt("id"));
				user.setNome(res.getString("nome"));
				user.setSobrenome(res.getString("sobrenome"));
				user.setUsuario(res.getString("usuario"));
				user.setEmail(res.getString("email"));
				user.setCpf(res.getString("cpf"));
				user.setSenha(res.getString("senha"));
				user.setConfirmarSenha(res.getString("confirmarSenha"));
				Date dataNascimento = res.getDate("dataNascimento");
				user.setDataNascimento(dataNascimento.toLocalDate());
				
				lista.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar admin(user/senha)" + e.getMessage());
		}

		return lista;
	}
}
