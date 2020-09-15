package persistencia.dao.mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDaoImpl implements LocalidadDAO {

	@Override
	public boolean update(LocalidadDTO dto) {
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call updateLocalidad(?,?)}");
			cstmt.setInt(1, dto.getId());
			cstmt.setString(2, dto.getNombre());
			if(cstmt.executeUpdate() > 0) {
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
	public boolean insert(LocalidadDTO dto) {
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call createLocalidad(?)}");
			cstmt.setString(1, dto.getNombre());
			if(cstmt.executeUpdate() > 0) {
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
	public boolean delete(LocalidadDTO dto) {
		boolean isdeleteExitoso = false;
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call deleteLocalidadById(?)}");
			cstmt.setInt(1, dto.getId());
			if(cstmt.executeUpdate() > 0) {
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
	public List<LocalidadDTO> readAll() {
		CallableStatement cstmt = null;
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call findAlllocalidades()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()) localidades.add(new LocalidadDTO(rs.getInt("LocalidadID"),rs.getString("LocalidadNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return localidades;
	}
}
