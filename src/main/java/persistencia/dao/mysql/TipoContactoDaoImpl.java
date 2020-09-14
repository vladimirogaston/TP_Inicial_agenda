package persistencia.dao.mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDaoImpl implements TipoContactoDAO {

	@Override
	public boolean update(TipoContactoDTO tContacto) {
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call updateTipoContacto(?,?)}");
			cstmt.setInt(1, tContacto.getId());
			cstmt.setString(2, tContacto.getNombre());
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
	public boolean insert(TipoContactoDTO tContacto) {
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call createTipoContacto(?) }");
			cstmt.setString(1, tContacto.getNombre());
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
	public boolean delete(TipoContactoDTO tContacto) {
		boolean isdeleteExitoso = false;
		CallableStatement cstmt = null;
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call deleteTipoContactoById(?)}");
			cstmt.setInt(1, tContacto.getId());
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
	public List<TipoContactoDTO> readAll() {
		CallableStatement cstmt = null;
		ArrayList<TipoContactoDTO> lst = new ArrayList<>();
		try {
			cstmt  = Conexion.getConexion().getSQLConexion().prepareCall("{call findAllTiposContacto()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()) lst.add(new TipoContactoDTO(rs.getInt("TipoContactoID"),rs.getString("TipoContactoNombre")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lst;
	}

}
