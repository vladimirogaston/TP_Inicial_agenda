package repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDataSource extends DataSource {

	String driver;
	String host;
	String port;
	String user;
	String password;
	String database;
	
	public MySqlDataSource dbName(String db) {
		this.database = db;
		return this;
	}
	
	public MySqlDataSource driver(String driver) {
		this.driver = driver;
		return this;
	}
	
	public MySqlDataSource port(String port) {
		this.port = port;
		return this;
	}
	
	public MySqlDataSource pass(String pass) {
		this.password = pass;
		return this;
	}
	
	public MySqlDataSource user(String user) {
		this.user = user;
		return this;
	}
	
	public MySqlDataSource host(String host) {
		this.host = host;
		return this;
	}
	
	@Override
	protected Connection stablishConnection() {
		Connection conn = null;
		try	{
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/grupo_11", user, password);
			conn.setAutoCommit(false);
			System.out.println("Conexión exitosa");
		} catch(Exception e) {
			System.out.println("Conexión fallida");
		}
		return conn;
	}
}
