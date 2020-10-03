package repositories.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DataSource extends DataSource {

	static final String driver = "org.h2.Driver";
	static final String url = "jdbc:h2:~/test";
	static final String scriptdb = "INIT=RUNSCRIPT FROM 'classpath:script.sql'";
	static final String user= "sa";
	static final String password = "";
		
	@Override
	protected void initConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url+";"+scriptdb, "sa", "");
		} catch (SQLException e) {
			System.out.println("Connection fails: " + e.getMessage());
		} catch (ClassNotFoundException e1) {
			System.out.println("Error driver registry: " + e1.getMessage());
		}
	}
}