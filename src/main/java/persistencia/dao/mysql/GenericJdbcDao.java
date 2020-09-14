package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericJdbcDao {
	
	Connection conexion = Conexion.getConexion().getSQLConexion();
	
	protected abstract PreparedStatement buildStatement();

	public boolean excecuteStatement() {
		try	{
			if(buildStatement().executeUpdate() > 0) {
				conexion.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
}
