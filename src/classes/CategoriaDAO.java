package classes;

import java.sql.SQLException;


public class CategoriaDAO extends GenericDao<Categoria>{

	public CategoriaDAO(String databaseUrl) throws SQLException {
		super(databaseUrl, Categoria.class);
	}
}
