package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import daoUtil.ConnectionFactory;
import model.Admin;

public class LoginDao {

	private Connection con;
	private Statement stm;

	Connection connection = null;

	public LoginDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlLogar = "SELECT * FROM admin WHERE usuario = usurario AND senha = senha";

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

	public Admin login(String login, String senha) throws SQLException {
		Admin usuario = null;
		String sql = "SELECT * FROM admin WHERE usuario =? AND senha=?";
		con.setAutoCommit(false);
		PreparedStatement ts = (PreparedStatement) con.prepareStatement(sql);
		ts.setObject(1, usuario);
		ts.setObject(2, senha);
		ResultSet rs = ts.executeQuery();
		if (rs.next()) {
			usuario = new Admin();
			usuario.setUsuario("usuario");
			usuario.setSenha("123456");
			usuario.addAdm(usuario);
		}
		return usuario;
	}
}
