package repositories.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDataSource extends DataSource {

	static final String driver = "com.mysql.jdbc.Driver";
	static final String host = "localhost";
	static final String port = "3306";
	static final String user= "root";
	static final String password = "root";
	static final String database = "grupo_11";
	
	@Override
	protected void initConnection() {
		try {
            Class.forName(driver);
            DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex.getMessage());
        } catch (SQLException e) {
        	System.out.println("Error al iniciar MySQL server: " + e.getMessage());
		}
	}
}
