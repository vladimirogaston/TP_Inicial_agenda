package repositories;

import dto.PersonaDTO;

public interface PersonaDao extends GenericDao<PersonaDTO, Integer>{

	PersonaDTO readByPhone(String telefono);
}
