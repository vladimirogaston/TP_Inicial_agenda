package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.PersonaDTO;
import repositories.PersonaDao;

public class PersonaDaoImpl extends GenericJdbcDao<PersonaDTO> implements PersonaDao {

	static final String SELECT = "SELECT idPersona, Nombre, Telefono, Email, FechaCumpleaños, TipoContactoNombre, Calle, Altura, Piso, Departamento, LocalidadNombre,\r\n" + 
			"ProvinciaNombre, PaisNombre, EquipoFutbol, CodigoPostal \r\n" + 
			"FROM personas P LEFT JOIN TiposContacto T ON P.TipoContactoID = T.TipoContactoID\r\n" + 
			"LEFT JOIN Localidades L ON P.LocalidadID = L.LocalidadID\r\n" + 
			"LEFT JOIN Provincia Q ON P.ProvinciaID = Q.ProvinciaID\r\n" + 
			"LEFT JOIN Pais K ON P.PaisID = K.PaisID";
	
	static final String insert = "INSERT INTO personas (Nombre,Telefono,Email,FechaCumpleaños,TipoContactoID,Calle,Altura,Piso,Departamento,LocalidadID,\r\n" + 
			"ProvinciaID,PaisID,EquipoFutbol,CodigoPostal) VALUES (?,?,?,?, (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = ?),\r\n" + 
			"?,?,?,?,(SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = ?),(SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?),(SELECT PaisID FROM Pais WHERE Pais.PaisNombre = ?), ?, ?)";

	static final String update = "UPDATE personas\r\n" + 
			"	SET Nombre = ?, Telefono = ?, Email = ?, FechaCumpleaños = ?, Calle = ?\r\n" + 
			"	,Altura = ?, Piso = ?, Departamento = ?\r\n" + 
			"	,TipoContactoID = (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = ?)\r\n" + 
			"	,LocalidadID = (SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = ?)\r\n" + 
			"	,ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = ?)\r\n" + 
			"	,PaisID = (SELECT PaisID FROM Pais WHERE Pais.PaisNombre = ?)\r\n" + 
			"	,EquipoFutbol = ?\r\n" + 
			"	,CodigoPostal = ?\r\n" + 
			"	WHERE idPersona = ?";
	
	static final String delete = "DELETE FROM personas WHERE personas.idPersona = ?";
	static final String readAll = SELECT + " " + "ORDER BY CodigoPostal";
	static final String readById =  SELECT + " " + "WHERE P.idPersona = ?";
	static final String readByPhone = SELECT + " "+ "WHERE P.Telefono = ?";
		
	private Mapper<PersonaDTO> mapper;
	
	public PersonaDaoImpl(Connection connection) {
		super(connection);
		mapper = getMapper();
	}
	
	@Override
	public boolean insert(PersonaDTO p) {
		 return getTemplate()
				.query(insert)
				.param(p.getNombre())
				.param(p.getTelefono())
				.param(p.getEmail())
				.param(p.getFechaNacimiento() != null ? new java.sql.Date(p.getFechaNacimiento().getTime()) : null)
				.param(p.getTipoContacto())
				.param(p.getCalle())
				.param(p.getAltura())
				.param(p.getPiso())
				.param(p.getDpto())
				.param(p.getLocalidad())
				.param(p.getProvincia())
				.param(p.getPais())
				.param(p.getEquipoFutbol())
				.param(p.getCodigoPostalInteger() == null ? 0 : p.getCodigoPostalInteger())
				.param(p.getTipoContacto())				
				.excecute();
	}
	
	@Override
	public boolean update(PersonaDTO p) {
		return getTemplate()
				.query(update)
				.param(p.getNombre())
				.param(p.getTelefono())
				.param(p.getEmail())
				// TODO revisar porque se rompe al pasar un null como parametro
				.param(p.getFechaNacimiento() != null ? new java.sql.Date(p.getFechaNacimiento().getTime()) : null)
				.param(p.getTipoContacto())
				.param(p.getCalle())
				.param(p.getAltura())
				.param(p.getPiso())
				.param(p.getDpto())
				.param(p.getLocalidad())
				.param(p.getProvincia())
				.param(p.getPais())
				.param(p.getEquipoFutbol())
				.param(p.getCodigoPostalInteger() == null ? 0 : p.getCodigoPostalInteger())
				.param(p.getId())
				.excecute();
		}
	
	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}
	
	@Override
	public List<PersonaDTO> readAll() {
		return getTemplate().query(readAll).excecute(mapper);
	}

	@Override
	public PersonaDTO readByID(Integer id) {
		return getData(getTemplate().query(readById).param(id).excecute(mapper));
	}

	@Override
	public PersonaDTO readByPhone(String telefono) {
		return getData(getTemplate().query(readByPhone).param(telefono).excecute(mapper));
	}
	
	@Override
	protected Mapper<PersonaDTO> getMapper() {
		return new Mapper<PersonaDTO>() {
			@Override
			public PersonaDTO map(Object[] obj) {
				return new PersonaDTO.Builder((String)obj[1], (String)obj[2])
						.id((Integer) obj[0])
						.email((String) obj[3])
						.fechaNacimiento((Date) obj[4])
						.tipoContacto((String) obj[5])
						.calle((String) obj[6])
						.altura((String) obj[7])
						.piso((String) obj[8])
						.dpto((String) obj[9])
						.localidad((String) obj[10])
						.provincia((String) obj[11])
						.pais((String) obj[12])
						.equipoFutbol((String) obj[13])
						.codigoPostal(((Integer) obj[14]).toString())
						.build();
			}			
		};
	}
	
	private PersonaDTO getData(List<PersonaDTO> personas) {
		assert personas != null;
		if(personas.isEmpty()) return null;
		return personas.get(0);
	}
}