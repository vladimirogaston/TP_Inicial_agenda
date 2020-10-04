package business_logic.local;

import java.util.List;

import business_logic.PaisController;
import dto.PaisDTO;

public class ObservablePaisController  extends ObservableImpl implements PaisController {

	private PaisController controller;

	public ObservablePaisController(PaisController controller) {
		this.controller = controller;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return controller.deleteById(id);
	}

	@Override
	public List<PaisDTO> readAll() {		
		return controller.readAll();
	}

	@Override
	public boolean save(PaisDTO entity) {
		boolean ret = controller.save(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean update(PaisDTO entity) {
		boolean ret = controller.update(entity);
		notifyObservers();
		return ret;
	}
}
