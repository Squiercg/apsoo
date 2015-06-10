package repositorio;

import java.sql.SQLException;

import classes.ItemLote;

public class ItemLoteProdutoDao extends GenericDao<ItemLote> {
	
	public ItemLoteProdutoDao(String databaseUrl) throws SQLException {
		super(databaseUrl, ItemLote.class);
	}
}
