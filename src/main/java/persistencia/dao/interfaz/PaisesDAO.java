package persistencia.dao.interfaz;

import java.util.List;

import dto.PaisDTO;

public interface PaisesDAO {
	
	boolean update(PaisDTO tContacto);

	boolean insert(PaisDTO tContacto);

	boolean delete(PaisDTO tContacto);

	List<PaisDTO> readAll();
}
