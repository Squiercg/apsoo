package repositorio;

import java.io.File;
import java.io.IOException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class TesteOrmlite {
	
	// URL do Banco de dados	
	private String systemDatabaseURL = "jdbc:sqlite:";
	private final static String DATABASE_URL = "jdbc:sqlite:";
	
	@SuppressWarnings("unused")
	private Dao<CategoriaEntity, Integer> categoriaDao;
	
	public static void main(String[] args) throws Exception {
		new TesteOrmlite().doMain(args);	
	}	
	
	private void doMain(String[] args) throws Exception {
		setSystemDatabaseURL();
		Conector conectorDao = new Conector(systemDatabaseURL);
		conectorDao.CriaConexao();
		setupDatabase(conectorDao.getConexao());	
	}
	
	private void setSystemDatabaseURL() {
		try {
			systemDatabaseURL += new File("lib/.").getCanonicalPath() + "\\" + "CDT_database.sqlite";
		}
		catch(IOException e) {
		}
	}
	
	private void setupDatabase(ConnectionSource connectionSource) throws Exception {
		// Configura o Dao com a conexão
		categoriaDao = DaoManager.createDao(connectionSource, CategoriaEntity.class);
		// Cria a tabela se a mesma não existir
		TableUtils.createTableIfNotExists(connectionSource, CategoriaEntity.class);
	}

}