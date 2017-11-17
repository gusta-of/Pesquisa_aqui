package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoUtil.ConnectionFactory;
import model.Admin;

public class LoginDao {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;

	Connection connection = null;

	public LoginDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlLogar = "SELECT * FROM admin WHERE usuario = usuario AND senha = senha";

	public List<Admin> listarAdmin() {
		List<Admin> lista = new ArrayList<Admin>();
		ResultSet res = null;
		try {
			if (this.con != null) {
				stm = con.createStatement();
				res = stm.executeQuery(sqlLogar);
				while (res.next()) {
					Admin login = new Admin();
					login.setUsuario(res.getString("usuario"));
					login.setSenha(res.getString("senha"));

					lista.add(login);
				}
			}
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro na consulta SQL!" + e.getMessage());
			return new ArrayList<Admin>();
		}
	}

	public boolean login(Admin admin) throws SQLException {
		String sql = "SELECT usuario, senha FROM admin WHERE usuario = usuario AND senha = senha";
		
		con.setAutoCommit(false);
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, admin.getUsuario());
		stmt.setString(2, admin.getSenha());
		
		stmt.executeUpdate();
		con.commit();
			
		return true;
	}
}
