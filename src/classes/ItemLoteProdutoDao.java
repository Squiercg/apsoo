package classes;

import java.sql.SQLException;

public class ItemLoteProdutoDao extends GenericDao<ItemLote>{

	public ItemLoteProdutoDao(String databaseUrl) throws SQLException {
		super(databaseUrl, ItemLote.class);
	}

}
