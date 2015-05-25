package repositorio;

import java.sql.SQLException;

import classes.Lote;

public class LoteDao extends GenericDao<Lote>{

	public LoteDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Lote.class);
	}	
	
	public int insertLote(Lote lote) throws SQLException {
		super.insert(lote);
		return lote.getLoteId();
	}

}
