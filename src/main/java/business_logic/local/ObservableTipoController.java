package business_logic.local;

import java.util.List;

import business_logic.TipoController;
import dto.TipoContactoDTO;

public class ObservableTipoController extends ObservableImpl implements TipoController {

	private TipoController controller;

	public ObservableTipoController(TipoController controller) {
		this.controller = controller;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return controller.deleteById(id);
	}

	@Override
	public List<TipoContactoDTO> readAll() {		
		return controller.readAll();
	}

	@Override
	public boolean save(TipoContactoDTO entity) {
		boolean ret = controller.save(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean update(TipoContactoDTO entity) {
		boolean ret = controller.update(entity);
		notifyObservers();
		return ret;
	}
}
