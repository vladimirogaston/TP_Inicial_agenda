package repositories;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDao extends GenericDao<ProvinciaDTO, Integer>{

	List<ProvinciaDTO> readByPais(String pais);
}
