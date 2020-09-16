package persistencia.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {

	static final String insert = "{call createPersona(?,?,?,?,?,?,?,?,?,?) }";
	static final String update = "{call updatePersona(?,?,?,?,?,?,?,?,?,?,?) }";
	static final String delete = "{call deletePersonaById(?)}";
	static final String find = "{call findAllPersonas()}";
	Connection conexion = Conexion.getConexion().getSQLConexion();

	@Override
	public boolean insert(PersonaDTO p) {
		CallableStatement cstmt = null;
		try {
			cstmt = Conexion.getConexion().getSQLConexion().prepareCall(insert);
			cstmt.setString(1, p.getNombre());
			cstmt.setInt(2, Integer.parseInt(p.getTelefono()));
			cstmt.setString(3, p.getEmail());
			cstmt.setDate(4, new java.sql.Date(p.getFechaNacimiento().getTime()));
			cstmt.setString(5, p.getTipoContacto());
			cstmt.setString(6, p.getCalle());
			cstmt.setInt(7, Integer.parseInt(p.getAltura()));
			cstmt.setInt(8, Integer.parseInt(p.getPiso()));
			cstmt.setString(9, p.getDpto());
			cstmt.setString(10, p.getLocalidad());
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
			cstmt.setInt(3, Integer.parseInt(p.getTelefono()));
			cstmt.setString(4, p.getEmail());
			cstmt.setDate(5, new java.sql.Date(p.getFechaNacimiento().getTime()));
			cstmt.setString(6, p.getTipoContacto());
			cstmt.setString(7, p.getCalle());
			cstmt.setInt(8, Integer.parseInt(p.getAltura()));
			cstmt.setInt(9, Integer.parseInt(p.getPiso()));
			cstmt.setString(10, p.getDpto());
			cstmt.setString(11, p.getLocalidad());
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

	@Override
	public List<PersonaDTO> readAll() {
		CallableStatement cstmt = null;
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			String SQL = find;
			cstmt = conexion.prepareCall(SQL);
			ResultSet resultSet;
			resultSet = cstmt.executeQuery();
			while (resultSet.next()) {
				personas.add(getPersonaDTO(resultSet));
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
		return personas;
	}

	PersonaDTO getPersonaDTO(ResultSet rs) throws SQLException {
		return new PersonaDTO.Builder(rs.getString("Nombre"), rs.getString("Telefono")).id(rs.getInt("idPersona"))
				.email(rs.getString("Email")).fechaNacimiento(rs.getDate("FechaCumpleaños"))
				.tipoContacto(rs.getString("TipoContactoNombre")).calle(rs.getString("Calle"))
				.altura(Integer.valueOf(rs.getInt("Altura")).toString())
				.piso(Integer.valueOf(rs.getInt("Piso")).toString()).dpto(rs.getString("Departamento"))
				.localidad(rs.getString("LocalidadNombre")).build();
	}
}
