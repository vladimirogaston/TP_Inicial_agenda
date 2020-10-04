package business_logic.local;

import java.util.List;

import business_logic.LocalidadController;
import dto.LocalidadDTO;

public class ObservableLocalidadController extends ObservableImpl implements LocalidadController {

	private LocalidadController controller;

	public ObservableLocalidadController(LocalidadController controller) {
		this.controller = controller;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return controller.deleteById(id);
	}

	@Override
	public List<LocalidadDTO> readAll() {		
		return controller.readAll();
	}

	@Override
	public boolean save(LocalidadDTO entity) {
		boolean ret = controller.save(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean update(LocalidadDTO entity) {
		boolean ret = controller.update(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public List<LocalidadDTO> readByProvincia(String provincia) {
		return controller.readByProvincia(provincia);
	}
}
