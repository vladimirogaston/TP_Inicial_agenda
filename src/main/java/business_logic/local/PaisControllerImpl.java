package business_logic.local;

import java.util.List;

import business_logic.ForbiddenException;
import business_logic.NotFoundException;
import business_logic.PaisController;
import dto.PaisDTO;
import repositories.PaisDao;

public class PaisControllerImpl implements PaisController {

	private PaisDao dao;
	
	public PaisControllerImpl(PaisDao dao) {
		assert dao != null;
		this.dao = dao;
	}	
	
	@Override
	public boolean save(PaisDTO paisDTO) {
		assert paisDTO != null;
		PaisDTO target = dao.readByName(paisDTO.getNombre());
		if(target != null) {
			throw new ForbiddenException("No se puede utilizar un nombre de pais que ya está en uso");
		}
		return dao.insert(paisDTO);
	}

	@Override
	public boolean update(PaisDTO paisDTO) {
		assert paisDTO != null;
		PaisDTO target = dao.readByName(paisDTO.getNombre());
		if(target != null) {
			throw new ForbiddenException("No se puede utilizar un nombre de pais que ya está en uso");
		}
		if(dao.readByID(paisDTO.getId()) == null) {
			throw new NotFoundException("Pais no encontrado.");
		}
		return dao.update(paisDTO);
	}
	
	@Override
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	@Override
	public List<PaisDTO> readAll() {
		return dao.readAll();
	}
}