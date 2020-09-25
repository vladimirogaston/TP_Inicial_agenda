package repositories.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.TipoContactoDTO;
import repositories.TipoContactoDao;

public class TipoContactoDaoImpl implements TipoContactoDao {

	static final String insert = "INSERT INTO TiposContacto (TipoContactoNombre) VALUES(?)";
	static final String update = "UPDATE TiposContacto SET TipoContactoNombre = ? WHERE TipoContactoID = ?";
	static final String delete = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
	static final String readall = "SELECT * FROM TiposContacto";
	static final String readbyid = "SELECT * FROM TiposContacto WHERE TipoContactoID = ?";
	Connection connection = Conexion.getConexion().getSQLConexion();
	
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
		return new JdbcTemplate(connection).excecutableQuery(delete, new JdbcTemplate.Param<Integer>(1, id));
	}

	@Override
	public boolean delete(TipoContactoDTO entity) {
		String query = "DELETE FROM TiposContacto WHERE TipoContactoNombre = ?";
		return new JdbcTemplate(connection).excecutableQuery(query, new JdbcTemplate.Param<String>(1, entity.getNombre()));
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
		// TODO Auto-generated method stub
		return null;
	}
}
