package classes;

import java.sql.SQLException;


public class CategoriaDao extends GenericDao<Categoria>{

	public CategoriaDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Categoria.class);
	}
}
