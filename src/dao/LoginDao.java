package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import daoUtil.ConnectionFactory;

public class LoginDao {
   
   private PreparedStatement stmt;
   private Connection con;
   private Statement stm;
   
   Connection connection = null;
   
   public LoginDao() {
      ConnectionFactory cf = new ConnectionFactory();
      con = cf.getConnection();
   }
   
   String sqlLogar = "SELECT * FROM admin WHERE usuario = usurario AND senha = senha";
   
}
