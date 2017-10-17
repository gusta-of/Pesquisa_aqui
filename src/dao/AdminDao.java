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

public class AdminDao {
	
	ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;
    
    public AdminDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }


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


    public String Editar(Admin adm) {
        String deletado = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlDeletar);

            stmt.setInt(1, cliente.getId());

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

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getEmail());
            stmt.setInt(9, cliente.getId());

            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";


        }catch (Exception e){
            System.out.println("erro ao atualizar " + e.getMessage());
            salvo = e.getMessage();
        }
        return salvo;
    }
	
}
