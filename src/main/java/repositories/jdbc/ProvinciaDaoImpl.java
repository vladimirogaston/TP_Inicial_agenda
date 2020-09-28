package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaDaoImpl extends GenericJdbcDao<ProvinciaDTO> implements ProvinciaDao {

	static final String SELECT = "SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia LEFT JOIN Pais ON Provincia.PaisID = Pais.PaisID";
	static final String insert = "INSERT INTO Provincia(ProvinciaNombre, PaisID) VALUES (?, (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?))";
	static final String update = "UPDATE Provincia SET ProvinciaNombre = ?,	PaisID = (SELECT PaisID FROM Pais P WHERE P.PaisNombre = ?) WHERE ProvinciaID = ?";
	static final String delete = "DELETE FROM Provincia WHERE ProvinciaID = ?";
	static final String readAll = SELECT;
	static final String readById = SELECT + " " + "WHERE ProvinciaID = ?";
	static final String readByName = SELECT + " " + "WHERE Provincia.ProvinciaNombre = ?";
	static final String readByPais = SELECT + " " + "WHERE Pais.PaisNombre = ?";
	
	private Mapper<ProvinciaDTO> mapper;
	
	public ProvinciaDaoImpl(Connection connection) {
		super(connection);
		mapper = getMapper();
	}

	@Override
	public boolean insert(ProvinciaDTO dto) {
		assert dto != null;
		return getTemplate().query(insert).param(dto.getNombre()).param(dto.getPais())
				.excecute();
	}
	
	@Override
	public boolean update(ProvinciaDTO dto) {
		assert dto != null;
		return getTemplate().query(update).param(dto.getNombre()).param(dto.getPais())
				.param(dto.getId())
				.excecute();
	}
	
	@Override
	public boolean deleteById(Integer id) {
		assert id != null;
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public ProvinciaDTO readByID(Integer id) {
		assert id != null;
		return getData(getTemplate().query(readById).param(id).excecute(mapper));
	}

	@Override
	public List<ProvinciaDTO> readAll() {
		return getTemplate().query(readAll).excecute(mapper);
	}

	@Override
	public List<ProvinciaDTO> readByPais(String pais) {
		assert pais != null;
		return getTemplate().query(readByPais).param(pais).excecute(mapper);
	}

	@Override
	public ProvinciaDTO readByName(String nombre) {
		assert nombre != null;
		return getData(getTemplate().query(readByName).param(nombre).excecute(mapper));
	}

	@Override
	protected Mapper<ProvinciaDTO> getMapper() {
		return new Mapper<ProvinciaDTO>() {

			@Override
			public ProvinciaDTO map(Object[] obj) {
				return new ProvinciaDTO((Integer) obj[0], (String) obj[1], (String) obj[2]);
			}
		};
	}
	
	private ProvinciaDTO getData(List<ProvinciaDTO> provincias) {
		assert provincias != null;
		if(provincias.isEmpty()) return null;
		return provincias.get(0);
	}
}