package repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import dto.ConfigDatabaseDTO;

public class Conexion {
	
	private static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
		
	public static Conexion getConexion(ConfigDatabaseDTO conf) {
		if (instancia == null) {
			instancia = new Conexion(conf);
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	private Conexion(ConfigDatabaseDTO conf) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = 
					DriverManager.getConnection("jdbc:mysql://" + conf.getIp() + ":" + conf.getPort() + "/grupo_11"
							,conf.getUser(),conf.getPassword());
			this.connection.setAutoCommit(false);
			log.info("Conexión exitosa");
		} catch (Exception e) {
			log.info("Conexión fallida");
			throw new DatabaseException("Conexion fallida");
		}
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		} catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
}
