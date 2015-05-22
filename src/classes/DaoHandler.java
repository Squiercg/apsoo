package classes;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

public class DaoHandler {
	private String databaseUrl;
	private String tableName;
	private JdbcConnectionSource connectionSource;
	
	public DaoHandler(String databaseUrl, String tableName) throws SQLException {
		DriverManager.registerDriver(new org.sqlite.JDBC());
		this.databaseUrl = databaseUrl;
		this.tableName = tableName;
		connectionSource = new JdbcPooledConnectionSource("jdbc:sqlite:" + databaseUrl);
	}
	
	Dao<Venda, Integer> vendaDao = DaoManager.createDao(connectionSource, Venda.class);
	Dao<Fornecedor, Integer> fornecedorDao = DaoManager.createDao(connectionSource, Fornecedor.class);
}
