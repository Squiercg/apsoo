package repositorio;

import java.sql.SQLException;

import classes.Categoria;


public class CategoriaDao extends GenericDao<Categoria>{

	public CategoriaDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Categoria.class);
	}
}
