
package business_logic.local;

import java.util.List;

import business_logic.ForbiddenException;
import business_logic.NotFoundException;
import business_logic.PersonaController;
import dto.PersonaDTO;
import repositories.DaosFactory;
import repositories.PersonaDao;

public class PersonaControllerImpl implements PersonaController {
	
	private static final String NOT_FOUND = "Persona no encontrada";
	private static final String FORBIDDEN = "No se puede utilizar un teléfono que ya está en uso";
	private PersonaDao dao;
	
	public PersonaControllerImpl(DaosFactory dao) {
		this.dao = dao.createPersonaDAO();
	}
			
	@Override
	public boolean save(PersonaDTO personaDto) {
		PersonaDTO target = dao.readByPhone(personaDto.getTelefono());
		if(target != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		return dao.insert(personaDto);
	}

	@Override
	public boolean update(PersonaDTO personaDto) {
		if(dao.readByID(personaDto.getId()) == null) {
			throw new NotFoundException(NOT_FOUND);
		}
		if(personaDto.getTelefono() != null && personaDto.getTelefono().isEmpty()) {
			PersonaDTO target = dao.readByPhone(personaDto.getNombre());
			if(target != null) {
				throw new ForbiddenException(FORBIDDEN);
			}	
		}
		return dao.update(personaDto);
	}

	@Override
	public boolean deleteById(Integer id) {
		return dao.deleteById(id);	
	}
	
	@Override
	public List<PersonaDTO> readAll() {
		return dao.readAll();
	}
}