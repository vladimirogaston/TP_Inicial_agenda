package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoContactoDTO;

public interface TipoContactoDAO {

	boolean update(TipoContactoDTO tContacto);

	boolean insert(TipoContactoDTO tContacto);

	boolean delete(TipoContactoDTO tContacto);

	List<TipoContactoDTO> readAll();
}
