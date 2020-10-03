package repositories.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataSource {

	protected static Connection connection;
	
	protected abstract void initConnection();
	
	public Connection getConnection() {
		if(connection == null) {
			initConnection();
			System.out.println("Connection open");
		}
		return connection;
	}

	public void CloseConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}