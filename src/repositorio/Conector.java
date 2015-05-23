package repositorio;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Conector {	
	
	private String DATABASE_URL;
	private ConnectionSource conexao;
	
	public Conector(String dataBaseUrl){
		this.DATABASE_URL = dataBaseUrl;
	}

	public boolean CriaConexao() throws Exception {
		conexao = null;
		try {
			// create our data-source for the database
			conexao = new JdbcConnectionSource(DATABASE_URL);
			return true;
		} catch (Exception ex){			
			return false;
		}
	}
	
	public void FechaConexao() throws Exception{
		if (conexao != null) 
			conexao.close();
	}
	
	public ConnectionSource getConexao() {
		return conexao;
	}
}
