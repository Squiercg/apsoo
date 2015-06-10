package repositorio;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

public abstract class GenericDao<E> {
	
	protected Dao<E, Integer> dao;
	private Class<E> type;
	private JdbcConnectionSource connectionSource;
	
	public GenericDao(String databaseUrl, Class<E> type) throws SQLException {
		this.type = type;
		connectionSource = new JdbcConnectionSource(databaseUrl);
		setDao();
	}
	
	protected void setDao() {
		try {
			dao = DaoManager.createDao(connectionSource, type);
		} catch(Exception e) {
			e.printStackTrace();
			finaliza();
		}
	}
	
	public int insert(E data) throws SQLException {
		return dao.create(data);
	}
	
	public List<E> getAll() {
		try {
			List<E> result = dao.queryForAll();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			finaliza();
		}
	}
	
	public List<E> getAllActive() {
		try {
			List<E> result = dao.queryForAll();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			finaliza();
		}
	}
	
	public List<E> getForValue(String field, String value) {
		try {
			return dao.queryForEq(field, value);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			finaliza();
		}
		return null;
	}
	
	public E getById(int id) {
		try {
			E obj = dao.queryForId(id);
			return obj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			finaliza();
		}
	}
	
	public int update(E data) {
		int rows = 0;
		try {
			rows = dao.update(data);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			finaliza();
		}
		return rows;
	}
	
	public int delete(E data) {
		int rows = 0;
		try {
			rows = dao.delete(data);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			finaliza();
		}
		return rows;
	}
	
	public int deleteById(int id){
		int rows = 0;
		try {
			rows = dao.deleteById(id);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			finaliza();
		}
		return rows;
	}
	
	private void finaliza() {
		try {
			connectionSource.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
