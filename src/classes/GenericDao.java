package classes;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public abstract class GenericDao<E> {

	protected Dao<E, Integer> dao;
	private Class<E> type;
	private ConnectionSource connectionSource;
 
	public GenericDao(String databaseUrl, Class<E> type) throws SQLException {
		this.type = type;
		connectionSource = new JdbcConnectionSource(databaseUrl);
		setDao();
	}
 
	//cria o DAO para o tipo solicitado
	protected void setDao() {
		try{
			dao = DaoManager.createDao(connectionSource, type);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Devolve todos os campos de uma tabela qualquer
	public List<E> getAll() {
		try{
			List<E> result = dao.queryForAll();
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			finaliza();
		}
	}
	
	// Devolve resultado pelo id solicitado
	public E getById(int id) {
		try{
			E obj = dao.queryForId(id);
			return obj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			finaliza();
		}
	}
	// Altera um dado no banco
	public int update(E data){
		int rows = 0;
		try {
			rows = dao.update(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			finaliza();
		}
		return rows;
	}
	
	//Apaga um registro completo no banco
	public int delete (E data){
		int rows = 0;
		try {
			rows = dao.delete(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			finaliza();
		}
		return rows;
	}
	
	//Apaga um registro pelo Id no banco
		public int deleteById (int id){
			int rows = 0;
			try {
				rows = dao.deleteById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				finaliza();
			}
			return rows;
		}
	
	private void finaliza(){
		try {
			connectionSource.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}