package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JdbcTemplate {

	private Connection connection;
	private String query;
	private Map<Integer, Param<?>> params;
	
	public JdbcTemplate(Connection conn) {
		assert connection != null;
		connection = conn;
		params = new HashMap<>();
	}
	
	public <T> JdbcTemplate param(T value) {
		int key =  params.size() + 1;
		if(value == null) params.put(key, new Param<T>(null));
		else params.put(key, new Param<T>(value));
		return this;
	}
	
	public JdbcTemplate query(String query) {
		assert query != null;
		this.query = query;
		return this;
	}
		
	public boolean excecute() {
		boolean isInsertExitoso = false;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			injectParams(statement);
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		return isInsertExitoso;
	}
	
	public <T> List<T> excecute(Mapper<T> mapper) {
		if(mapper == null) throw new IllegalArgumentException("El mapper no debe ser null");
		List<T> lst = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			injectParams(statement);
			ResultSet rs = statement.executeQuery();
			lst = map(rs, mapper);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	private <T> List<T> map(ResultSet resultSet, Mapper<T> mapper) throws SQLException {
		List<T> ret = new LinkedList<>();
		int columns = resultSet.getMetaData().getColumnCount();
		while (resultSet.next()) {
			Object[] arr = new Object [columns];
			for (int i = 0; i < columns; i++) {
				arr[i] = resultSet.getObject(i + 1);
			}
			ret.add(mapper.map(arr));
		}
		return ret;
	}
	
	private void injectParams(PreparedStatement statement) {
		params.forEach((k,v) -> {
			try {
				//TODO ¿Codigo espagetti...?
				if (v.type.equals(NullObject.class)) {
					statement.setObject(k, null);
				} else if (v.type.equals(Integer.class)) {
					statement.setInt(k, (Integer) v.value);
				} else 	if (v.type.equals(String.class)) {
					statement.setString(k, (String) v.value);
				} else 	if(v.type.equals(java.sql.Date.class)) {
					statement.setDate(k, (java.sql.Date) v.value);	
				}
			} catch (SQLException t) {
				t.printStackTrace();
			}
		});
	}
	
	private static class Param<T> {
		Class<? extends Object> type;
		T value;

		public Param(T val) {
			type = val.getClass();
			value = val;
		}
	}
}