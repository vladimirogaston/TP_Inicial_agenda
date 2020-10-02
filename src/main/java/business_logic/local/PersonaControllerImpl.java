
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
		assert dao != null;
		this.dao = dao.createPersonaDAO();
	}
			
	@Override
	public boolean save(PersonaDTO personaDto) {
		assert personaDto != null;
		PersonaDTO target = dao.readByPhone(personaDto.getTelefono());
		if(target != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		return dao.insert(personaDto);
	}

	@Override
	public boolean update(PersonaDTO personaDto) {
		assert personaDto != null;
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
	public void delete(int id) {
		try {
			dao.deleteById(id);	
		}catch(ForbiddenException e) {
			throw new ForbiddenException("No se puede eliminar la persona");
		}
	}
	
	@Override
	public List<PersonaDTO> readAll() {
		return dao.readAll();
	}
}