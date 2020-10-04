package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.TipoContactoDTO;
import repositories.TipoContactoDao;

public class TipoContactoDaoImpl extends GenericJdbcDao<TipoContactoDTO> implements TipoContactoDao {

	static final String insert = "INSERT INTO TiposContacto (TipoContactoNombre) VALUES(?)";
	static final String update = "UPDATE TiposContacto SET TipoContactoNombre = ? WHERE TipoContactoID = ?";
	static final String readAll = "SELECT * FROM TiposContacto";
	static final String readById = "SELECT * FROM TiposContacto WHERE TipoContactoID = ?";
	static final String readByName = readAll + " " + "WHERE TiposContacto.TipoContactoNombre = ?";
	static final String deleteById = "DELETE FROM TiposContacto WHERE TipoContactoID = ?";
	
	private Mapper<TipoContactoDTO> mapper;
	
	public TipoContactoDaoImpl(Connection connection) {
		super(connection);
		mapper = getMapper();
	}
	
	@Override
	public boolean insert(TipoContactoDTO dto) {
		return getTemplate().query(insert).param(dto.getNombre()).excecute();
	}

	@Override
	public boolean update(TipoContactoDTO dto) {
		return getTemplate().query(update).param(dto.getNombre()).param(dto.getId())
			.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(deleteById).param(id).excecute();
	}

	@Override
	public TipoContactoDTO readByID(Integer id) {
		return getData(getTemplate().query(readById).param(id).excecute(mapper));
	}

	@Override
	public List<TipoContactoDTO> readAll() {
		return getTemplate().query(readAll).excecute(mapper);
	}

	@Override
	public TipoContactoDTO readByName(String nombre) {
		return getData(getTemplate().query(readByName).param(nombre).excecute(mapper));
	}

	@Override
	protected Mapper<TipoContactoDTO> getMapper() {
		return new Mapper<TipoContactoDTO>() {
			@Override
			public TipoContactoDTO map(Object[] obj) {
				return new TipoContactoDTO((Integer) obj[0], (String) obj[1]);
			}
		};
	}
	
	private TipoContactoDTO getData(List<TipoContactoDTO> tipos) {
		if(tipos.isEmpty()) return null;
		return tipos.get(0);		
	}
}
