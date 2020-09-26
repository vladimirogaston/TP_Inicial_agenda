package repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import business_logic.exceptions.ForbiddenException;
import dto.TipoContactoDTO;
import repositories.TipoContactoDao;

public class TipoContactoDaoImpl implements TipoContactoDao {

	static final String insert = "INSERT INTO TiposContacto (TipoContactoNombre) VALUES(?)";
	static final String update = "UPDATE TiposContacto SET TipoContactoNombre = ? WHERE TipoContactoID = ?";
	static final String delete = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
	static final String readall = "SELECT * FROM TiposContacto";
	static final String readbyid = "SELECT * FROM TiposContacto WHERE TipoContactoID = ?";
	private Connection connection;
	
	public TipoContactoDaoImpl(Connection connection) {
		assert connection != null;
		this.connection = connection;
	}
	
	@Override
	public boolean insert(TipoContactoDTO dto) {
		return new JdbcTemplate(connection).excecutableQuery(insert, new JdbcTemplate.Param<String>(1, dto.getNombre()));
	}

	@Override
	public boolean update(TipoContactoDTO dto) {
		JdbcTemplate.Param<?> [] params = {
			new JdbcTemplate.Param<String>(1, dto.getNombre()),
			new JdbcTemplate.Param<Integer>(2, dto.getId())
		};
		return new JdbcTemplate(connection).excecutableQuery(update, params);
	}

	@Override
	public boolean deleteById(Integer id) {
		String query = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
		PreparedStatement statement;
		boolean isdeleteExitoso = false;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			if (statement.executeUpdate() > 0) {
				connection.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			throw new ForbiddenException("No se puede el tipo en uso.");
		}
		return isdeleteExitoso;
	}
	
	@Override
	public List<TipoContactoDTO> readAll() {
		return new ReadJdbcTemplate<TipoContactoDTO>(readall) {
			@Override
			protected TipoContactoDTO  makeReturnValueFromResultSet(ResultSet rs){
				TipoContactoDTO dto = null;
				try {
					dto =  new TipoContactoDTO(rs.getInt("TipoContactoID"), rs.getString("TipoContactoNombre"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return dto;
			}
		}.readAll();
	}

	@Override
	public TipoContactoDTO readByID(Integer id) {
		TipoContactoDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion()
					.prepareStatement("SELECT * FROM TiposContacto WHERE TipoContactoID = ?");
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
	public TipoContactoDTO readByName(String nombre) {
		TipoContactoDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion()
					.prepareStatement("SELECT * FROM TiposContacto WHERE TipoContactoNombre = ?");
			statement.setString(1, nombre);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new TipoContactoDTO(rs.getInt("TipoContactoID"), rs.getString("TipoContactoNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
