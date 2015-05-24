package repositorio;

import java.sql.SQLException;

import classes.Fornecedor;

public class FornecedorDao extends GenericDao<Fornecedor>{
	public FornecedorDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Fornecedor.class);
	}

}
