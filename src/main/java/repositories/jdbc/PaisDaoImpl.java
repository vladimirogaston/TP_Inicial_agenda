package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.DatabaseException;
import dto.PaisDTO;
import repositories.PaisDao;

public class PaisDaoImpl implements PaisDao {

	static final String insert = "INSERT INTO Pais (PaisNombre) VALUES(?)";
	static final String update = "UPDATE Pais SET PaisNombre = ? WHERE PaisID = ?";
	static final String delete = "DELETE FROM Pais WHERE PaisID = ?";
	final String readall = "SELECT * FROM Pais";
	static final String readbyid = "SELECT * FROM Pais WHERE PaisID = ?";
	
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
	public PaisDTO readByID(Integer id) {
		PaisDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readbyid);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new PaisDTO(rs.getInt("PaisID"), rs.getString("PaisNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
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

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
