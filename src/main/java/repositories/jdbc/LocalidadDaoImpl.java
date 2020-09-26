package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.exceptions.ForbiddenException;
import dto.LocalidadDTO;
import repositories.LocalidadDao;

public class LocalidadDaoImpl implements LocalidadDao {

	static final String insert = "INSERT INTO Localidades(LocalidadNombre, ProvinciaID) VALUES (?, (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?))";
	static final String update = "UPDATE Localidades SET LocalidadNombre = ?,	ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?) WHERE LocalidadID = ?";
	static final String delete = "DELETE FROM Localidades WHERE LocalidadID = ?";
	static final String readall = "SELECT LocalidadID, LocalidadNombre, ProvinciaNombre FROM Localidades L LEFT JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID";
	static final String readbyprovincia = "SELECT LocalidadID, LocalidadNombre, ProvinciaNombre FROM Localidades L LEFT JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID WHERE P.ProvinciaNombre = ?";
	static final String readbyid = "SELECT * FROM Localidades WHERE LocalidadID = ?";
	private Connection connection;
	
	public LocalidadDaoImpl(Connection connection) {
		assert connection != null;
		this.connection = connection;
	}

	@Override
	public boolean insert(LocalidadDTO dto) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(insert);
			statement.setString(1, dto.getNombre());
			statement.setString(2, dto.getProvincia());
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
			throw new ForbiddenException("La Localidad ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public boolean update(LocalidadDTO dto) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = connection.prepareStatement(update);
			statement.setString(1, dto.getNombre());
			statement.setString(2, dto.getProvincia());
			statement.setInt(3, dto.getId());
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
			throw new ForbiddenException("La Localidad ya existe.");
		}
		return isInsertExitoso;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		ArrayList<LocalidadDTO> lst = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(readall);
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
			PreparedStatement statement = connection.prepareStatement(readbyprovincia);
			statement.setString(1, provincia);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new LocalidadDTO(rs.getInt("LocalidadID"), rs.getString("LocalidadNombre"), rs.getString("ProvinciaNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public LocalidadDTO readByID(Integer id) {
		LocalidadDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(readbyid);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new LocalidadDTO(rs.getInt("LocalidadID"), rs.getString("LocalidadNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public LocalidadDTO readByName(String nombre) {
		LocalidadDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT LocalidadID, LocalidadNombre, ProvinciaNombre"
							+" FROM Localidades L LEFT JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID WHERE"
							+ " LocalidadNombre = ?");
			statement.setString(1, nombre);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new LocalidadDTO(rs.getInt("LocalidadID"), rs.getString("LocalidadNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
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
			throw new ForbiddenException("No se puede eliminar una Localidad en uso.");
		}
		return isdeleteExitoso;
	}
}
