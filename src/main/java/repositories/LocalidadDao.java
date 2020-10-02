package repositories;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDao extends GenericDao<LocalidadDTO, Integer> {

	List<LocalidadDTO> readPorProvincia(String provincia);

	LocalidadDTO readByName(String nombre);
}
