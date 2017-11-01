package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDao;
import model.Fornecedor;

public class FornecedorNegocio {
   
   Fornecedor fornecedor = new Fornecedor();
   
   private FornecedorDao fornecedorDao = new FornecedorDao();
   
   public List<Fornecedor> listarFornecedor() {
      List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
      fornecedores = fornecedorDao.listarFornecedor();
      return fornecedores;
   }
   
   public boolean salvar(Fornecedor fornecedor) throws SQLException {
      if (fornecedor.getId() != 0) {
         editar(fornecedor);
      } else {
         fornecedorDao.salvar(fornecedor);
      }
      return true;
   }
   
   public boolean editar(Fornecedor fornecedor) {
      return fornecedorDao.editar(fornecedor);
   }
   
   public boolean deletar(Fornecedor fornecedor) {
      
      return fornecedorDao.deletar(fornecedor);
   }
   
}
