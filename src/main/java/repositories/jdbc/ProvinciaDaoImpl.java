package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.exceptions.ForbiddenException;
import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaDaoImpl implements ProvinciaDao {

	static final String insert = "INSERT INTO Provincia(ProvinciaNombre, PaisID) VALUES (?, (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?))";
	static final String update = "UPDATE Provincia SET ProvinciaNombre = ?,	PaisID = (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?) WHERE ProvinciaID = ?";
	static final String delete = "DELETE FROM Provincia WHERE ProvinciaID = ?";
	static final String readall = "SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia LEFT JOIN Pais ON Provincia.PaisID = Pais.PaisID";
	static final String readbyid = "SELECT * FROM Provincia WHERE ProvinciaID = ?";
	private Connection connection;
	
	public ProvinciaDaoImpl(Connection connection) {
		assert connection != null;
		this.connection = connection;
	}
	
	@Override
	public boolean update(ProvinciaDTO provinciaDTO) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(update);
			statement.setString(1, provinciaDTO.getNombre());
			statement.setString(2, provinciaDTO.getPais());
			statement.setInt(3, provinciaDTO.getId());
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ForbiddenException("La Provincia ya existe.");
		}
		return isInsertExitoso;

	}
	
	@Override
	public boolean insert(ProvinciaDTO provinciaDTO) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(insert);
			statement.setString(1, provinciaDTO.getNombre());
			statement.setString(2, provinciaDTO.getPais());
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ForbiddenException("La Provincia ya existe.");
		}
		return isInsertExitoso;
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
			throw new ForbiddenException("No se puede eliminar una Provincia en uso.");
		}
		return isdeleteExitoso;

	}
		
	@Override
	public ProvinciaDTO readByID(Integer id) {
		ProvinciaDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(readbyid);
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
			PreparedStatement statement = connection.prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<ProvinciaDTO> readByPais(String pais) {
		ArrayList<ProvinciaDTO> lst = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia LEFT JOIN Pais ON Provincia.PaisID = Pais.PaisID WHERE PaisNombre = ?");
			statement.setString(1, pais);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public ProvinciaDTO readByName(String nombre) {
		ProvinciaDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia LEFT JOIN Pais ON Provincia.PaisID = Pais.PaisID WHERE ProvinciaNombre = ?");
			statement.setString(1, nombre);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"), rs.getString("PaisNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}	
}