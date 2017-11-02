package negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDao;
import model.Fornecedor;

public class FornecedorNegocio {
	
    FornecedorDao fornecedorDao = new FornecedorDao();
   public List<Fornecedor> listarFornecedor(){
	   List<Fornecedor> fornecedores = new ArrayList<>();
	   fornecedores = fornecedorDao.listarFornecedor();
	   return fornecedores;
   }

   public boolean salvar(Fornecedor fornecedor) throws ParseException, SQLException {
      if (fornecedor.getId() != 0) {
         editar(fornecedor);
         listarFornecedor();
      } else {
         fornecedorDao.salvar(fornecedor);
      }
      return true;
   }
   
   public boolean editar(Fornecedor fornecedor) throws SQLException {
      return fornecedorDao.editar(fornecedor);
   }
   
   public boolean deletar(Fornecedor fornecedor) {
      
      return fornecedorDao.deletar(fornecedor);
   }
   
}
