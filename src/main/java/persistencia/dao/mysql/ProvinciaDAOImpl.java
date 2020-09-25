package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProvinciaDTO;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOImpl implements ProvinciaDAO {

	final String readall = "SELECT ProvinciaID, ProvinciaNombre, PaisNombre FROM Provincia PR LEFT JOIN Pais PA ON PR.PaisID = PA.PaisID";
	static final String readbyid = "SELECT * FROM Provincia WHERE ProvinciaID = ?";
	
	@Override
	public boolean update(ProvinciaDTO p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(ProvinciaDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProvinciaDTO persona_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ProvinciaDTO readByID(Integer id) {
		ProvinciaDTO dto = null;
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readbyid);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				dto = new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<ProvinciaDTO> readAll() {
		ArrayList<ProvinciaDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new ProvinciaDTO(rs.getInt("ProvinciaID"), rs.getString("ProvinciaNombre"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
