package business_logic.local;

import java.util.List;

import business_logic.ForbiddenException;
import business_logic.LocalidadController;
import business_logic.NotFoundException;
import dto.LocalidadDTO;
import repositories.LocalidadDao;

public class LocalidadControllerImpl implements LocalidadController {

	private LocalidadDao dao;
	
	public LocalidadControllerImpl(LocalidadDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean save(LocalidadDTO localidadDTO) {
		LocalidadDTO target = dao.readByName(localidadDTO.getNombre());
		if(target != null) {
			throw new ForbiddenException("No se puede utilizar un nombre de pais que ya está en uso");
		}
		return dao.insert(localidadDTO);
	}

	@Override
	public boolean update(LocalidadDTO localidadDTO) {
		LocalidadDTO target = dao.readByName(localidadDTO.getNombre());
		if(target != null) {
			throw new ForbiddenException("No se puede utilizar un nombre de pais que ya está en uso");
		}
		if(dao.readByID(localidadDTO.getId()) == null) {
			throw new NotFoundException("Pais no encontrado.");
		}
		return dao.update(localidadDTO);
	}
		
	@Override
	public boolean deleteById(Integer id) {
		return dao.deleteById(id);
	}
	
	@Override
	public List<LocalidadDTO> readAll() {
		return dao.readAll();
	}
	
	@Override
	public List<LocalidadDTO> readByProvincia(String provincia) {
		return dao.readPorProvincia(provincia);
	}
}
