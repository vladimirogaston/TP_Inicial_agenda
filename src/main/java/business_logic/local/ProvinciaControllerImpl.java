package business_logic.local;

import java.util.List;

import business_logic.ForbiddenException;
import business_logic.NotFoundException;
import business_logic.ProvinciaController;
import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaControllerImpl implements ProvinciaController {

	private static final String FORBIDDEN = "No se puede utilizar un nombre que ya está en uso";
	private ProvinciaDao dao;
	
	public ProvinciaControllerImpl(ProvinciaDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean save(ProvinciaDTO provinciaDto) {
		ProvinciaDTO target = dao.readByName(provinciaDto.getNombre());
		if(target != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		return dao.insert(provinciaDto);
	}

	@Override
	public boolean update(ProvinciaDTO provinciaDto) {
		if(dao.readByName(provinciaDto.getNombre()) != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		if(dao.readByID(provinciaDto.getId()) == null) {
			throw new NotFoundException("No encontrado.");
		}
		return dao.update(provinciaDto);
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return dao.deleteById(id);
	}
	
	@Override
	public List<ProvinciaDTO> readAll() {
		return dao.readAll();
	}
	
	@Override
	public List<ProvinciaDTO> readByPais(String pais) {
		return dao.readByPais(pais);
	}
}
