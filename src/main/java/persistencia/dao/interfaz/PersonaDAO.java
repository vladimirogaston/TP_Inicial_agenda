package persistencia.dao.interfaz;

import java.util.List;

import dto.PersonaDTO;

public interface PersonaDAO {

	boolean update(PersonaDTO p);

	boolean insert(PersonaDTO persona);

	boolean delete(PersonaDTO persona_a_eliminar);

	List<PersonaDTO> readAll();
}
