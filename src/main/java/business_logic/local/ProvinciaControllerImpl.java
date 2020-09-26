package business_logic.local;

import java.util.List;

import business_logic.ProvinciaController;
import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaControllerImpl implements ProvinciaController {

	private static final String FORBIDDEN = "No se puede utilizar un nombre que ya está en uso";
	private ProvinciaDao dao;
	
	public ProvinciaControllerImpl(ProvinciaDao dao) {
		assert dao != null;
		this.dao = dao;
	}
	
	@Override
	public boolean save(ProvinciaDTO provinciaDto) {
		assert provinciaDto != null;
		ProvinciaDTO target = dao.readByName(provinciaDto.getNombre());
		if(target != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		return dao.insert(provinciaDto);
	}

	@Override
	public boolean update(ProvinciaDTO provinciaDto) {
		assert provinciaDto != null;
		if(dao.readByName(provinciaDto.getNombre()) != null) {
			throw new ForbiddenException(FORBIDDEN);
		}
		if(dao.readByID(provinciaDto.getId()) == null) {
			throw new NotFoundException("No encontrado.");
		}
		return dao.update(provinciaDto);
	}
	
	@Override
	public void delete(int id) {
		try{
			dao.deleteById(id);
		}catch(Throwable t) {
			throw t;
		}
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
