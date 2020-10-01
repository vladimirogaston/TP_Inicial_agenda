package business_logic.local;

import java.util.List;

import business_logic.ForbiddenException;
import business_logic.TipoController;
import dto.TipoContactoDTO;
import repositories.TipoContactoDao;

public class TipoControllerImpl implements TipoController {

	private TipoContactoDao dao;
	
	public TipoControllerImpl(TipoContactoDao dao) {
		assert dao != null;
		this.dao = dao;
	}
	
	@Override
	public boolean save(TipoContactoDTO tipoDto) {
		assert tipoDto != null;
		if(dao.readByName(tipoDto.getNombre()) != null) {
			throw new ForbiddenException("El tipo ya esta en uso.");
		}
		return dao.insert(tipoDto);
	}

	@Override
	public boolean update(TipoContactoDTO tipoDto) {
		assert tipoDto != null;
		return dao.update(tipoDto);
	}
	
	@Override
	public void delete(int id) {
		try {
			dao.deleteById(id);	
		}catch(Throwable t) {
			throw t;
		}
	}
	
	@Override
	public List<TipoContactoDTO> readAll() {
		return dao.readAll();
	}
}
