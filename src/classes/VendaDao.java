package classes;

import java.sql.SQLException;

public class VendaDao extends GenericDao<Venda>{

	public VendaDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Venda.class);
	}

}
