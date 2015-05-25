package repositorio;

import java.sql.SQLException;

import classes.Venda;

public class VendaDao extends GenericDao<Venda>{

	public VendaDao(String databaseUrl) throws SQLException {
		super(databaseUrl, Venda.class);
	}
	
	public int insertVenda(Venda venda) throws SQLException	{
		super.insert(venda);
		return venda.getVendaId();
	}
}
