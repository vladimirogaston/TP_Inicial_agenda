package business_logic;

import java.util.List;

import dto.PaisDTO;
import repositories.DaosFactory;
import repositories.PaisDao;

public class PaisController {

	private PaisDao dao;
	
	public PaisController() {
		dao = DaosFactory.getFactory().createPaisDAO();
	}	
	
	public void agregarPais(PaisDTO paisDTO) {
		dao.insert(paisDTO);
	}

	public void editarPais(PaisDTO paisDTO) {
		dao.update(paisDTO);
	}

	public void borrarPais(PaisDTO paisDTO) {
		dao.delete(paisDTO);
	}
	
	public List<PaisDTO> paisesDisponibles() {
		return dao.readAll();
	}
}
