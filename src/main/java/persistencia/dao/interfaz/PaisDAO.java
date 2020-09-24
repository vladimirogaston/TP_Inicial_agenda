package persistencia.dao.interfaz;

import java.util.List;

import dto.PaisDTO;

public interface PaisDAO {
	
	boolean update(PaisDTO pais);

	boolean insert(PaisDTO pais);

	boolean delete(PaisDTO pais);
	
	List<PaisDTO> readAll();
	
	PaisDTO readByID(Integer id);
}
