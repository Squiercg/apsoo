package repositorio;

import java.sql.SQLException;

import classes.Produto;

public class ProdutoDao extends GenericDao<Produto> {
	
	public ProdutoDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Produto.class);
	}
}
