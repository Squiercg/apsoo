package repositorio;

import java.sql.SQLException;

import classes.Cliente;

public class ClienteDao extends GenericDao<Cliente>{
	public ClienteDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Cliente.class);
	}

}
