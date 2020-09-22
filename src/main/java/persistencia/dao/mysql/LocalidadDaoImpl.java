package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.LocalidadDAO;
import presentacion.controlador.DatabaseException;

public class LocalidadDaoImpl implements LocalidadDAO {

	static final String insert = "INSERT INTO Localidades(LocalidadNombre) VALUES(?, ?)";
	static final String update = "UPDATE Localidades SET LocalidadNombre = ?,	ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?) WHERE LocalidadID = ?";
	static final String delete = "DELETE FROM Localidades WHERE LocalidadID = ?";
	static final String readall = "SELECT LocalidadID, LocalidadNombre, ProvinciaNombre FROM Localidades L INNER JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID";
	static final String readbyprovincia = "SELECT LocalidadID, LocalidadNombre, ProvinciaNombre FROM Localidades L INNER JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID WHERE P.ProvinciaNombre = ?";
	
	@Override
	public boolean insert(LocalidadDTO dto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, dto.getNombre());
			statement.setString(2, dto.getProvincia());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DatabaseException("La Localidad ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean update(LocalidadDTO dto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, dto.getNombre());
			statement.setInt(2, dto.getId());
			statement.setString(3, dto.getProvincia());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DatabaseException("La Localidad ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean delete(LocalidadDTO dto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, dto.getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			throw new DatabaseException("No se puede eliminar una Localidad en uso.");
		}
		return isdeleteExitoso;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		ArrayList<LocalidadDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new LocalidadDTO(rs.getInt("LocalidadID"), rs.getString("LocalidadNombre"), rs.getString("ProvinciaNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<LocalidadDTO> readPorProvincia(String provincia) {
		ArrayList<LocalidadDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readbyprovincia);
			statement.setString(1, provincia);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new LocalidadDTO(rs.getInt("LocalidadID"), rs.getString("LocalidadNombre"), rs.getString("ProvinciaNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
