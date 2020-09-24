package persistencia.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {

	static final String insert = "{call createPersona(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	static final String update = "{call updatePersona(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	static final String delete = "{call deletePersonaById(?)}";
	Connection conexion = Conexion.getConexion().getSQLConexion();

	@Override
	public boolean insert(PersonaDTO p) {
		CallableStatement cstmt = null;
		try {
			cstmt = Conexion.getConexion().getSQLConexion().prepareCall(insert);
			cstmt.setString(1, p.getNombre());
			cstmt.setString(2, p.getTelefono());
			cstmt.setString(3, p.getEmail());
			if(p.getFechaNacimiento() != null)cstmt.setDate(4, new java.sql.Date(p.getFechaNacimiento().getTime()));
			else cstmt.setDate(4,null);
			cstmt.setString(5, p.getTipoContacto());
			cstmt.setString(6, p.getCalle());
			cstmt.setString(7, p.getAltura());
			cstmt.setString(8, p.getPiso());
			cstmt.setString(9, p.getDpto());
			cstmt.setString(10, p.getLocalidad());
			cstmt.setString(11, p.getProvincia());
			cstmt.setString(12, p.getPais());
			cstmt.setString(13, p.getEquipoFutbol());
			cstmt.setString(14, p.getCodigoPostal());
			if (cstmt.executeUpdate() > 0) {
				conexion.commit();
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
	public boolean update(PersonaDTO p) {
		CallableStatement cstmt = null;
		try {
			cstmt = Conexion.getConexion().getSQLConexion().prepareCall(update);
			cstmt.setInt(1, p.getIdPersona());
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
            cstmt.setString(15, p.getCodigoPostal());
			if (cstmt.executeUpdate() > 0) {
				conexion.commit();
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
	public boolean delete(PersonaDTO p) {
		boolean isdeleteExitoso = false;
		CallableStatement cstmt = null;
		try {
			cstmt = Conexion.getConexion().getSQLConexion().prepareCall(delete);
			cstmt.setInt(1, p.getIdPersona());
			if (cstmt.executeUpdate() > 0) {
				conexion.commit();
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
	
	public List<Persona> readAllEntities() {
		PreparedStatement statement = null;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			statement = conexion.prepareStatement("SELECT * FROM personas");
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			while (resultSet.next()) { personas.add(getPersona(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	Persona getPersona(ResultSet rs) throws SQLException {
		Persona persona = new Persona();
		persona.setNombre(rs.getString("Nombre"));
		persona.setTelefono(rs.getString("Telefono"));
		persona.getIdPersona();
		persona.setEmail(rs.getString("Email"));
		persona.setFechaNacimiento(rs.getDate("FechaCumpleaños"));
		persona.setTipoContactoID(rs.getInt("TipoContactoID"));
		persona.setCalle(rs.getString("Calle"));
		persona.setAltura(rs.getString("Altura"));
		persona.setPiso(rs.getString("Piso"));
		persona.setDpto(rs.getString("Departamento"));
		persona.setLocalidadID(rs.getInt("LocalidadID"));
		persona.setProvinciaID(rs.getInt("ProvinciaID"));
		persona.setPaisID(rs.getInt("PaisID"));
		persona.setEquipoFutbol(rs.getString("EquipoFutbol"));
		persona.setCodigoPostal(rs.getString("CodigoPostal"));
		return persona;
	}
}
