package repositories;

import dto.TipoContactoDTO;

public interface TipoContactoDao extends GenericDao<TipoContactoDTO, Integer>{

	TipoContactoDTO readByName(String nombre);
}
