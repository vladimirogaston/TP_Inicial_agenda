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

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	Connection conexion = Conexion.getConexion().getSQLConexion();
		
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<PersonaDTO> readAll()
	{
		CallableStatement cstmt = null;
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			String SQL = "{ call findAllPersonas() }";
			cstmt  = conexion.prepareCall(SQL);
			ResultSet resultSet;
			resultSet = cstmt.executeQuery();
			while(resultSet.next()) {
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
	
	private PersonaDTO getPersonaDTO(ResultSet rs) throws SQLException {
		return new PersonaDTO.Builder(rs.getString("Nombre"), rs.getString("Telefono"))
				.id(rs.getInt("idPersona"))
				.email(rs.getString("Email"))
				.fechaNacimiento(rs.getDate("FechaCumpleaños"))
				.tipoContacto(rs.getString("TipoContactoNombre"))
				.calle(rs.getString("Calle"))
				.altura(Integer.valueOf(rs.getInt("Altura")).toString())
				.piso(Integer.valueOf(rs.getInt("Piso")).toString())
				.dpto(rs.getString("Departamento"))
				.localidad(rs.getString("LocalidadNombre"))
				.build();
	}

	@Override
	public boolean insert(PersonaDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}
}
