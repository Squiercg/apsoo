package classes;

import java.sql.SQLException;

public class FornecedorDao extends GenericDao<Fornecedor>{
	public FornecedorDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Fornecedor.class);
	}

}
