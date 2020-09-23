package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PaisDTO;
import persistencia.dao.interfaz.PaisDAO;
import presentacion.controlador.DatabaseException;

public class PaisDAOImpl implements PaisDAO {

	static final String insert = "INSERT INTO Pais (PaisNombre) VALUES(?)";
	static final String update = "UPDATE Pais SET PaisNombre = ? WHERE PaisID = ?";
	static final String delete = "DELETE FROM Pais WHERE PaisID = ?";
	final String readall = "SELECT * FROM Pais";
	
	@Override
	public boolean update(PaisDTO paisDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, paisDTO.getNombre());
			statement.setInt(2, paisDTO.getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
			}
			throw new DatabaseException("El Pais ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean insert(PaisDTO paisDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, paisDTO.getNombre());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
			}
			throw new DatabaseException("El Pais ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean delete(PaisDTO paisDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, paisDTO.getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			throw new DatabaseException("No se puede eliminar un Pais en uso.");
		}
		return isdeleteExitoso;
	}

	@Override
	public List<PaisDTO> readAll() {
		ArrayList<PaisDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new PaisDTO(rs.getInt("PaisID"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
