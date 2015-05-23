package classes;

import java.sql.SQLException;

public class ProdutoDao extends GenericDao<Produto>{

	public ProdutoDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Produto.class);
	}

}
