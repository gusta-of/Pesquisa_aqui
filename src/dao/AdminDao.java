package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Admin;

public class AdminDao {

	private Connection con;
	private Statement stm;
	public String salvar(Admin admin) {

		return "salvo";

	}

	public List<Admin> listarAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		ResultSet res = null;
		try {
			stm = con.createStatement();
			res = stm.executeQuery("SELECT * FROM Admin");
			while (res.next()) {
				Admin admin = new Admin();

				admin.setNome(res.getString("nome"));
				admin.setSobrenome(res.getString("sobrenome"));
				admin.setEmail(res.getString("email"));
				admin.setCpf(res.getString("cpf"));
				admin.setUser(res.getString("user"));

				list.add(admin);
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta 1:" + e.getMessage());
		}
		return list;
	}
}
