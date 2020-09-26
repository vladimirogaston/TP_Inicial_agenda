package repositories;

import java.util.List;

import dto.PersonaDTO;
import repositories.jdbc.Persona;

public interface PersonaDao extends GenericDao<PersonaDTO, Integer>{

	PersonaDTO readByPhone(String telefono);
}
