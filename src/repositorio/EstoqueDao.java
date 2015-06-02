package repositorio;
	
import java.sql.SQLException;

import classes.Estoque;

public class EstoqueDao extends GenericDao<Estoque> {

	public EstoqueDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Estoque.class);
	}
}
