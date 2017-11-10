package dao;

import java.sql.Connection;
import java.sql.Statement;

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
   
   public String login(Admin admin) {
      String login = "falha";
      return null;
   }
}
