package business_logic.local;

import java.util.List;

import business_logic.ProvinciaController;
import dto.ProvinciaDTO;

public class ObservableProvinciaController extends ObservableImpl implements ProvinciaController {

	private ProvinciaController controller;

	public ObservableProvinciaController(ProvinciaController controller) {
		this.controller = controller;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return controller.deleteById(id);
	}

	@Override
	public List<ProvinciaDTO> readAll() {		
		return controller.readAll();
	}

	@Override
	public boolean save(ProvinciaDTO entity) {
		boolean ret = controller.save(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean update(ProvinciaDTO entity) {
		boolean ret = controller.update(entity);
		notifyObservers();
		return ret;
	}

	@Override
	public List<ProvinciaDTO> readByPais(String pais) {
		return controller.readByPais(pais);
	}
}

