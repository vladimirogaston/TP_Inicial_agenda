package repositories.jdbc;

import java.sql.Connection;
import java.util.List;
import dto.LocalidadDTO;
import repositories.LocalidadDao;

public class LocalidadDaoImpl extends GenericJdbcDao<LocalidadDTO> implements LocalidadDao {

	static final String SELECT = "SELECT LocalidadID, LocalidadNombre, ProvinciaNombre FROM Localidades L LEFT JOIN Provincia P ON L.ProvinciaID = P.ProvinciaID";
	static final String insert = "INSERT INTO Localidades(LocalidadNombre, ProvinciaID) VALUES (?, (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?))";
	static final String update = "UPDATE Localidades SET LocalidadNombre = ?,	ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?) WHERE LocalidadID = ?";
	static final String deleteById = "DELETE FROM Localidades WHERE LocalidadID = ?";
	static final String readAll = SELECT;
	static final String readByProvincia = SELECT + " " + "WHERE P.ProvinciaNombre = ?";
	static final String readById = SELECT + " " + "WHERE LocalidadID = ?";
	static final String readByName = SELECT + " " + "WHERE L.LocalidadNombre = ?"; 
	
	private Mapper<LocalidadDTO> mapper;
	
	public LocalidadDaoImpl(Connection connection) {
		super(connection);
		mapper = getMapper();
	}

	@Override
	public boolean insert(LocalidadDTO dto) {
		return getTemplate().query(insert).param(dto.getNombre()).param(dto.getProvincia()).excecute();
	}

	@Override
	public boolean update(LocalidadDTO dto) {
		return getTemplate().query(update).param(dto.getNombre()).param(dto.getProvincia())
				.param(dto.getId()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(deleteById).param(id).excecute();
	}

	@Override
	public List<LocalidadDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public LocalidadDTO readByID(Integer id) {
		return getData(getTemplate().query(readById).param(id).excecute(mapper));
	}

	@Override
	public List<LocalidadDTO> readPorProvincia(String provincia) {
		return getTemplate().query(readByProvincia).param(provincia).excecute(mapper);
	}

	@Override
	public LocalidadDTO readByName(String nombre) {
		return getData(getTemplate().query(readByName).param(nombre).excecute(mapper));
	}

	@Override
	protected Mapper<LocalidadDTO> getMapper() {
		return new Mapper<LocalidadDTO>() {
			@Override
			public LocalidadDTO map(Object[] obj) {
				LocalidadDTO dto = new LocalidadDTO((Integer) obj[0], (String) obj[1], (String) obj[2]);
				return dto;
			}
		};
	}
	
	private LocalidadDTO getData(List<LocalidadDTO> localidades) {
		if(localidades.isEmpty()) return null;
		return localidades.get(0);
	}
}