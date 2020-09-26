package business_logic;

import java.util.List;

import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.PersonaDTO;
import repositories.DaosFactory;
import repositories.PersonaDao;

public class PersonaControllerImpl implements PersonaController {
	
	private static final String TELEFONO_EN_USO = "No se puede utilizar un teléfono que ya está en uso";
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
			throw new ForbiddenException(TELEFONO_EN_USO);
		}
		return dao.insert(target);
	}

	@Override
	public boolean update(PersonaDTO personaDto) {
		assert personaDto != null;
		if(dao.readByID(personaDto.getId()) == null) {
			throw new NotFoundException("Persona no encontrada");
		}
		if(personaDto.getTelefono() != null && personaDto.getTelefono().isEmpty()) {
			PersonaDTO target = dao.readByPhone(personaDto.getNombre());
			if(target != null) {
				throw new ForbiddenException(TELEFONO_EN_USO);
			}	
		}
		return dao.update(personaDto);
	}

	@Override
	public boolean delete(PersonaDTO personaDto) {
		assert personaDto != null;
		if(personaDto.getId() != null && dao.readByID(personaDto.getId()) != null) {
			return dao.deleteById(personaDto.getId());
		}
		return false;
	}
	
	@Override
	public List<PersonaDTO> readAll() {
		return dao.readAll();
	}
}