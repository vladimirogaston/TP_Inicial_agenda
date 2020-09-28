package repositories.jdbc;

import java.sql.Connection;

import repositories.GenericDao;

public abstract class GenericJdbcDao<T> implements GenericDao<T, Integer> {
	
	private Connection connection;
		
	public GenericJdbcDao(Connection connection) {
		super();
		assert connection != null;
		this.connection = connection;
	}

	protected abstract Mapper<T> getMapper();
	
	protected JdbcTemplate getTemplate() {
		return new JdbcTemplate(this.connection);
	}
}
