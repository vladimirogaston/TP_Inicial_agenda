package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.DatabaseException;
import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaDaoImpl implements ProvinciaDao {

	static final String insert = "INSERT INTO Provincia(ProvinciaNombre, PaisID) VALUES (?, (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?))";
	static final String update = "UPDATE Provincia SET ProvinciaNombre = ?,	PaisID = (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?) WHERE ProvinciaID = ?";
	static final String delete = "DELETE FROM Provincia WHERE ProvinciaID = ?";
	final String readall = "SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia PR LEFT JOIN Pais PA ON PR.PaisID = PA.PaisID";
	static final String readbyid = "SELECT * FROM Provincia WHERE ProvinciaID = ?";
	
	@Override
	public boolean update(ProvinciaDTO provinciaDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, provinciaDTO.getNombre());
			statement.setString(2, provinciaDTO.getPais());
			statement.setInt(3, provinciaDTO.getId());
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
			throw new DatabaseException("La Provincia ya existe.");
		}
		return isInsertExitoso;

	}
	
	@Override
	public boolean insert(ProvinciaDTO provinciaDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, provinciaDTO.getNombre());
			statement.setString(2, provinciaDTO.getPais());
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
			throw new DatabaseException("La Provincia ya existe.");
		}
		return isInsertExitoso;
	}
	
	@Override
	public boolean delete(ProvinciaDTO provinciaDTO) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, provinciaDTO.getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			throw new DatabaseException("No se puede eliminar una Provincia en uso.");
		}
		return isdeleteExitoso;

	}
	
	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ProvinciaDTO readByID(Integer id) {
		ProvinciaDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readbyid);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	public List<ProvinciaDTO> readAll() {
		ArrayList<ProvinciaDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}