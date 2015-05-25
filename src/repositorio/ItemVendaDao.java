package repositorio;

import java.sql.SQLException;

import classes.ItemVenda;

public class ItemVendaDao extends GenericDao<ItemVenda> {

	public ItemVendaDao(String databaseUrl) throws SQLException {
		super(databaseUrl, ItemVenda.class);
	}
}
