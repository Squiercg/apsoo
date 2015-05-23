package classes;

import java.sql.SQLException;

public class ClienteDao extends GenericDao<Cliente>{
	public ClienteDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Cliente.class);
	}

}
