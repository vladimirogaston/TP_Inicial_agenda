package repositories;

import dto.PaisDTO;

public interface PaisDao extends GenericDao<PaisDTO, Integer> {

	PaisDTO readByName(String nombre);
}
