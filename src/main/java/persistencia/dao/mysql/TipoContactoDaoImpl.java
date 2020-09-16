package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.dao.interfaz.TipoContactoDAO;
import presentacion.controlador.DatabaseException;

public class TipoContactoDaoImpl implements TipoContactoDAO {

	static final String insert = "INSERT INTO TiposContacto (TipoContactoNombre) VALUES(?)";
	static final String update = "UPDATE TiposContacto SET TipoContactoNombre = ? WHERE TipoContactoID = ?";
	static final String delete = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
	static final String readall = "SELECT * FROM TiposContacto";

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
				e1.printStackTrace();
			}
			throw new DatabaseException("El nombre del tipo de contacto ya esta en uso.");
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
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
			throw new DatabaseException("Error: no se puede eliminar un tipo en uso.");
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
}
