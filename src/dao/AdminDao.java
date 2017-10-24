package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import daoUtil.ConnectionFactory;

import model.Admin;

public class AdminDao {

	private PreparedStatement stmt;

	private Connection con;
	private Statement stm;
	ConnectionFactory connection = null;

	public AdminDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlSalvar = "INSERT INTO pesquisa_aqui.admin"
			+ "(nome,sobrenome, usuario, email,  cpf, senha, confirmarSenha, dataNascimento)" + "VALUES(?,?,?,?,?,?,?,?)";

	String sqlEditar = "UPDATE admin SET nome = ?, sobrenome = ?,"
			+ "usuario = ?, email = ?, cpf = ?, senha = ?," + "confirmarSenha = ? dataNascimento = ?";

	String sqlDeletar = "DELETE from admins where id = ?";

	

	public List<Admin> listarAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		ResultSet res = null;
		try {
			if (con != null) {
				stm = con.createStatement();
				res = stm.executeQuery("SELECT * FROM admin");
				while (res.next()) {
					Admin admin = new Admin();

					admin.setNome(res.getString("nome"));
					admin.setSobrenome(res.getString("sobrenome"));
					admin.setUsuario(res.getString("usuario"));
					admin.setEmail(res.getString("email"));
					admin.setCpf(res.getString("cpf"));
					admin.setSenha(res.getString("senha"));
					admin.setConfirmarSenha(res.getString("confirmarSenha"));
					Date dataNascimento = res.getDate("dataNascimento");
					admin.setDataNascimento(dataNascimento.toLocalDate());
					list.add(admin);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta 1:" + e.getMessage());
		}
		return list;
	}

	public String salvar(Admin admin) throws SQLException {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);

			stmt.setString(1, admin.getNome());
			stmt.setString(2, admin.getSobrenome());
			stmt.setString(3, admin.getUsuario());
			stmt.setString(4, admin.getEmail());
			stmt.setString(5, admin.getCpf());
			stmt.setString(6, admin.getSenha());
			stmt.setString(7, admin.getConfirmarSenha());
			stmt.setDate(8, Date.valueOf(admin.getDataNascimento()));

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (Exception e) {
			System.out.println("erro ao atualizar " + e.getMessage());
			salvo = e.getMessage();
		}
		return salvo;
	}

    public String editar(Admin admin) {
        String deletado = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlDeletar);

            stmt.setString(1, admin.getCpf());

            stmt.executeUpdate();
            con.commit();
            deletado = "deletado";

        } catch (SQLException e) {
            System.out.println("Erro na exclusão :" + e.getMessage());
            deletado = e.getMessage();
        }

        return deletado;
    }
	

	public String deletar(Admin admin) throws SQLException {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlEditar);

			stmt.setString(1, admin.getNome());
			stmt.setString(2, admin.getSobrenome());
			stmt.setString(3, admin.getCpf());
			stmt.setString(4, admin.getEmail());
			stmt.setString(5, admin.getUsuario());
			stmt.setString(6, admin.getSenha());
			stmt.setNString(7, admin.getConfirmarSenha());

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (Exception e) {
			System.out.println("erro ao atualizar " + e.getMessage());
			salvo = e.getMessage();
		}
		return salvo;
	}
}
