package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProvinciaDTO;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOImpl implements ProvinciaDAO {

	final String readall = "SELECT * FROM Provincia";
	
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
	public List<ProvinciaDTO> readAll() {
		ArrayList<ProvinciaDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new ProvinciaDTO(rs.getInt("PovinciaID"), rs.getString("ProvinciaNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
