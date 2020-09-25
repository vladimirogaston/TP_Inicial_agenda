package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.DatabaseException;
import dto.TipoContactoDTO;
import repositories.TipoContactoDao;

public class TipoContactoDaoImpl implements TipoContactoDao {

	static final String insert = "INSERT INTO TiposContacto (TipoContactoNombre) VALUES(?)";
	static final String update = "UPDATE TiposContacto SET TipoContactoNombre = ? WHERE TipoContactoID = ?";
	static final String delete = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
	static final String readall = "SELECT * FROM TiposContacto";
	static final String readbyid = "SELECT * FROM TiposContacto WHERE TipoContactoID = ?";

	@Override
	public boolean insert(TipoContactoDTO dto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, dto.getNombre());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
			}
			throw new DatabaseException("El Tipo de Contacto ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean update(TipoContactoDTO dto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, dto.getNombre());
			statement.setInt(2, dto.getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
			}
			throw new DatabaseException("El Tipo de Contacto ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean delete(TipoContactoDTO dto) throws DatabaseException {
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
			throw new DatabaseException("No se puede eliminar un Tipo de Contacto en uso.");
		}
		return isdeleteExitoso;
	}

	@Override
	public List<TipoContactoDTO> readAll() {
		ArrayList<TipoContactoDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new TipoContactoDTO(rs.getInt("TipoContactoID"), rs.getString("TipoContactoNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public TipoContactoDTO readByID(Integer id) {
		TipoContactoDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readbyid);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new TipoContactoDTO(rs.getInt("TipoContactoID"), rs.getString("TipoContactoNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}
}
