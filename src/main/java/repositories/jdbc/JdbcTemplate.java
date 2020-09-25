package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {

	static Connection connection;
	
	public JdbcTemplate(Connection conn) {
		super();
		assert connection != null;
		connection = conn;
	}
	
	public static class Param<T> {
		Class<? extends Object> type;
		int position;
		T value;

		public Param(int pos, T val) {
			type = val.getClass();
			position = pos;
			value = val;
		}
	}

	public boolean excecutableQuery(String query, Param<?>... params) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(query);
			for (Param<?> param : params) {
				if (param.type.equals(Integer.class)) {
					Integer val = (Integer) param.value;
					statement.setInt(param.position, val);
				}
				if (param.type.equals(String.class)) {
					String val = (String) param.value;
					statement.setString(param.position, val);
				}
				if(param.type.equals(java.sql.Date.class)) {
					java.sql.Date val = (java.sql.Date) param.value;
					statement.setDate(param.position, val);
				}
			}
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ConstraintViolationException(e.getMessage());
		}
		return isInsertExitoso;
	}
}
