package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PaisDTO;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOImpl implements PaisDAO {

	final String readall = "SELECT * FROM Pais";
	
	@Override
	public boolean update(PaisDTO pais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(PaisDTO pais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PaisDTO pais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PaisDTO> readAll() {
		ArrayList<PaisDTO> lst = new ArrayList<>();
		try {
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				lst.add(new PaisDTO(rs.getInt("PaisID"), rs.getString("PaisNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
