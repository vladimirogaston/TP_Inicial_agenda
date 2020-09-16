package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	boolean update(LocalidadDTO localidad);

	boolean insert(LocalidadDTO localidad);

	boolean delete(LocalidadDTO localidad);

	List<LocalidadDTO> readAll();
}
