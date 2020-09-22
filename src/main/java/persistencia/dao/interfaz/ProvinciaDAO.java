package persistencia.dao.interfaz;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDAO {

	boolean update(ProvinciaDTO p);

	boolean insert(ProvinciaDTO persona);

	boolean delete(ProvinciaDTO persona_a_eliminar);

	List<ProvinciaDTO> readAll();
}
