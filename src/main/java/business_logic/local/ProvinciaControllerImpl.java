package business_logic.local;

import java.util.List;

import business_logic.ProvinciaController;
import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.ProvinciaDTO;
import repositories.ProvinciaDao;

public class ProvinciaControllerImpl implements ProvinciaController {

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
			throw new ForbiddenException("No se puede utilizar un nombre que ya está en uso");
		}
		return dao.insert(provinciaDto);
	}

	@Override
	public boolean update(ProvinciaDTO provinciaDto) {
		assert provinciaDto != null;
		ProvinciaDTO target = dao.readByName(provinciaDto.getNombre());
		if(target != null) {
			throw new ForbiddenException("No se puede utilizar un nombre que ya está en uso");
		}
		if(dao.readByID(provinciaDto.getId()) == null) {
			throw new NotFoundException("No encontrado.");
		}
		return dao.update(provinciaDto);
	}
	
	@Override
	public boolean delete(ProvinciaDTO provinciaDto) {
		assert provinciaDto != null;
		if(provinciaDto.getId() == null) {
			try{
				return dao.deleteById(provinciaDto.getId());
			}catch(ForbiddenException e) {
				throw new ForbiddenException("No se puede eliminar una entidad en uso");
			}
		}
		return false;
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
