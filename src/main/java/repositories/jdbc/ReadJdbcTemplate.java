package repositories.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ReadJdbcTemplate<T> {

	String query;
	
	public ReadJdbcTemplate(String sql) {
		query = sql;
	}
	
	public List<T> readAll() {
		ArrayList<T> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) lst.add(makeReturnValueFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	protected abstract T makeReturnValueFromResultSet(ResultSet rs);
}
