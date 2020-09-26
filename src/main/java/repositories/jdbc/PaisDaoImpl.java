package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.exceptions.ForbiddenException;
import dto.PaisDTO;
import repositories.PaisDao;

public class PaisDaoImpl implements PaisDao {

	static final String insert = "INSERT INTO Pais (PaisNombre) VALUES(?)";
	static final String update = "UPDATE Pais SET PaisNombre = ? WHERE PaisID = ?";
	static final String delete = "DELETE FROM Pais WHERE PaisID = ?";
	static final String readall = "SELECT * FROM Pais";
	static final String readbyid = "SELECT * FROM Pais WHERE PaisID = ?";
	private Connection connection;	
	
	public PaisDaoImpl(Connection connection) {
		assert connection != null;
		this.connection = connection;
	}
	
	@Override
	public boolean update(PaisDTO paisDTO) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(update);
			statement.setString(1, paisDTO.getNombre());
			statement.setInt(2, paisDTO.getId());
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			}
			throw new ForbiddenException("El Pais ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean insert(PaisDTO paisDTO) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(insert);
			statement.setString(1, paisDTO.getNombre());
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			}
			throw new ForbiddenException("El Pais ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public PaisDTO readByID(Integer id) {
		PaisDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(readbyid);
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
			PreparedStatement statement = connection.prepareStatement(readall);
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
		PreparedStatement statement;
		boolean isdeleteExitoso = false;
		try {
			statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			throw new ForbiddenException("No se puede eliminar un Pais en uso.");
		}
		return isdeleteExitoso;
	}

	@Override
	public PaisDTO readByName(String nombre) {
		PaisDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Pais WHERE PaisNombre = ?");
			statement.setString(1, nombre);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new PaisDTO(rs.getInt("PaisID"), rs.getString("PaisNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
