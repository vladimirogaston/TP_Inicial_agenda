package repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDataSource extends DataSource {

	static final String driver = "com.mysql.jdbc.Driver";
	static final String host = "localhost";
	static final String port = "3306";
	static final String user= "root";
	static final String password = "root";
	static final String database = "grupo_11";
	
	@Override
	protected Connection stablishConnection() {
		Connection conn = null;
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo_11","root","root");
			conn.setAutoCommit(false);
			System.out.println("Conexión exitosa");
		} catch(Exception e) {
			System.out.println("Conexión fallida");
		}
		return conn;
	}
}
