package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.PaisDTO;
import repositories.PaisDao;

public class PaisDaoImpl extends GenericJdbcDao<PaisDTO> implements PaisDao {

	static final String insert = "INSERT INTO Pais (PaisNombre) VALUES (?)";
	static final String update = "UPDATE Pais SET PaisNombre = ? WHERE PaisID = ?";
	static final String delete = "DELETE FROM Pais WHERE PaisID = ?";
	static final String readall = "SELECT * FROM Pais";
	static final String readbyid = "SELECT * FROM Pais WHERE PaisID = ?";
	static final String readByName = "SELECT * FROM Pais WHERE PaisNombre = ?";
	
	private Mapper<PaisDTO> mapper;
	
	public PaisDaoImpl(Connection connection) {
		super(connection);
		mapper = getMapper();
	}

	@Override
	public boolean update(PaisDTO dto) {
		return getTemplate().query(update).param(dto.getNombre()).param(dto.getId()).excecute();
	}

	@Override
	public boolean insert(PaisDTO dto) {
		return getTemplate().query(insert).param(dto.getNombre()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public PaisDTO readByID(Integer id) {
		return getData(getTemplate().query(readbyid).param(id).excecute(mapper));
	}

	@Override
	public List<PaisDTO> readAll() {
		return getTemplate().query(readall).excecute(mapper);
	}

	@Override
	public PaisDTO readByName(String nombre) {
		return getData(getTemplate().query(readByName).param(nombre).excecute(mapper));
	}
	
	@Override
	protected Mapper<PaisDTO> getMapper() {
		return new Mapper<PaisDTO>() {
			@Override
			public PaisDTO map(Object[] obj) {
				return new PaisDTO((Integer) obj[0], (String) obj[1]);
			}
		};
	}
	
	private PaisDTO getData(List<PaisDTO> paises) {
		if(paises.isEmpty()) return null;
		return paises.get(0);
	}
}