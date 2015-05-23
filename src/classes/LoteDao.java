package classes;

import java.sql.SQLException;

public class LoteDao extends GenericDao<Lote>{

	public LoteDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Lote.class);
	}
	

}
