package repositories.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import repositories.PersonaDao;

public class PersonaDaoImpl implements PersonaDao {

	static final String insert = "{call createPersona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	static final String update = "{call updatePersona(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	static final String delete = "{call deletePersonaById(?)}";
	static final String readAll = "SELECT idPersona, Nombre, Telefono, Email, FechaCumpleaños, TipoContactoNombre, Calle, Altura, Piso, Departamento, LocalidadNombre,\r\n" + 
			"ProvinciaNombre, PaisNombre, EquipoFutbol, CodigoPostal \r\n" + 
			"FROM personas P LEFT JOIN TiposContacto T ON P.TipoContactoID = T.TipoContactoID\r\n" + 
			"LEFT JOIN Localidades L ON P.LocalidadID = L.LocalidadID\r\n" + 
			"LEFT JOIN Provincia Q ON P.ProvinciaID = Q.ProvinciaID\r\n" + 
			"LEFT JOIN Pais K ON P.PaisID = K.PaisID";
	static final String readById = "SELECT idPersona, Nombre, Telefono, Email, FechaCumpleaños, TipoContactoNombre, Calle, Altura, Piso, Departamento, LocalidadNombre,\r\n" + 
			"ProvinciaNombre, PaisNombre, EquipoFutbol, CodigoPostal \r\n" + 
			"FROM personas P LEFT JOIN TiposContacto T ON P.TipoContactoID = T.TipoContactoID\r\n" + 
			"LEFT JOIN Localidades L ON P.LocalidadID = L.LocalidadID\r\n" + 
			"LEFT JOIN Provincia Q ON P.ProvinciaID = Q.ProvinciaID\r\n" + 
			"LEFT JOIN Pais K ON P.PaisID = K.PaisID WHERE P.idPersona = ?";
	static final String readByPhone = "SELECT idPersona, Nombre, Telefono, Email, FechaCumpleaños, TipoContactoNombre, Calle, Altura, Piso, Departamento, LocalidadNombre,\r\n" + 
			"ProvinciaNombre, PaisNombre, EquipoFutbol, CodigoPostal \r\n" + 
			"FROM personas P LEFT JOIN TiposContacto T ON P.TipoContactoID = T.TipoContactoID\r\n" + 
			"LEFT JOIN Localidades L ON P.LocalidadID = L.LocalidadID\r\n" + 
			"LEFT JOIN Provincia Q ON P.ProvinciaID = Q.ProvinciaID\r\n" + 
			"LEFT JOIN Pais K ON P.PaisID = K.PaisID WHERE persona.Telefono = ?";
	
	
	private Connection connection;
	
	public PersonaDaoImpl(Connection connection) {
		assert connection != null;
		this.connection = connection;
	}
	
	@Override
	public boolean insert(PersonaDTO p) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insert);
			System.out.println("---------->>> " + p.getNombre() == null);
			statement.setString(1, p.getNombre());
			statement.setString(2, p.getTelefono());
			statement.setString(3, p.getEmail());
			if(p.getFechaNacimiento() != null)statement.setDate(4, new java.sql.Date(p.getFechaNacimiento().getTime()));
			statement.setDate(4, null);
			statement.setString(5, p.getTipoContacto());
			statement.setString(6, p.getCalle());
			statement.setString(7, p.getAltura());
			statement.setString(8, p.getPiso());
			statement.setString(9, p.getDpto());
			statement.setString(10, p.getLocalidad());
			statement.setString(11, p.getProvincia());
			statement.setString(12, p.getPais());
			statement.setString(13, p.getEquipoFutbol());
			statement.setInt(14, p.getCodigoPostalInteger() == null ? 0 : p.getCodigoPostalInteger());
			if (statement.executeUpdate() > 0) {
				connection.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean update(PersonaDTO p) {
		CallableStatement cstmt = null;
		try {
			cstmt = connection.prepareCall(update);
			cstmt.setInt(1, p.getId());
			cstmt.setString(2, p.getNombre());
			cstmt.setString(3, p.getTelefono());
			cstmt.setString(4, p.getEmail());
			if(p.getFechaNacimiento() != null)cstmt.setDate(5, new java.sql.Date(p.getFechaNacimiento().getTime()));
			cstmt.setDate(5, null);
			cstmt.setString(6, p.getTipoContacto());
			cstmt.setString(7, p.getCalle());
			cstmt.setString(8, p.getAltura());
			cstmt.setString(9, p.getPiso());
			cstmt.setString(10, p.getDpto());
			cstmt.setString(11, p.getLocalidad());
			cstmt.setString(12, p.getProvincia());
			cstmt.setString(13, p.getPais());
			cstmt.setString(14, p.getEquipoFutbol());
			cstmt.setInt(15, p.getCodigoPostalInteger() == null ? 0 : p.getCodigoPostalInteger());
			if (cstmt.executeUpdate() > 0) {
				connection.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		boolean isdeleteExitoso = false;
		CallableStatement cstmt = null;
		try {
			cstmt = connection.prepareCall(delete);
			cstmt.setInt(1, id);
			if (cstmt.executeUpdate() > 0) {
				connection.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isdeleteExitoso;
	}
	
	@Override
	public List<PersonaDTO> readAll() {
		PreparedStatement statement = null;
		ArrayList<PersonaDTO> personas = new ArrayList<>();
		try {
			statement = connection.prepareStatement(readAll);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) { personas.add(getPersona(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	@Override
	public PersonaDTO readByID(Integer id) {
		PersonaDTO persona = null;
		try {
			PreparedStatement statement = connection.prepareStatement(readById);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) { persona = getPersona(resultSet); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;
	}
	
	@Override
	public PersonaDTO readByPhone(String phone) {
		PersonaDTO persona = null;
		try {
			PreparedStatement statement = connection.prepareStatement(readById);
			statement.setString(1, phone);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) { persona = getPersona(resultSet); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;
	}
	
	PersonaDTO getPersona(ResultSet rs) throws SQLException {
		return new PersonaDTO.Builder(rs.getString("Nombre"), rs.getString("Telefono"))
				.id(rs.getInt("idPersona"))
				.email(rs.getString("Email"))
				.fechaNacimiento(rs.getDate("FechaCumpleaños"))
				.tipoContacto(rs.getString("TipoContactoNombre"))
				.calle(rs.getString("Calle"))
				.altura(rs.getString("Altura"))
				.piso(rs.getString("Piso"))
				.dpto(rs.getString("Departamento"))
				.localidad(rs.getString("LocalidadNombre"))
				.provincia(rs.getString("ProvinciaNombre"))
				.pais(rs.getString("PaisNombre"))
				.equipoFutbol(rs.getString("EquipoFutbol"))
				.codigoPostal(Integer.toString(rs.getInt("CodigoPostal")))
				.build();
	}
}