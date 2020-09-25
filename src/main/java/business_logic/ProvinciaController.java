package business_logic;

import java.util.List;

import dto.ProvinciaDTO;
import repositories.DaosFactory;
import repositories.ProvinciaDao;

public class ProvinciaController {

	private ProvinciaDao dao;
	
	public ProvinciaController() {
		dao = DaosFactory.getFactory().createProvinciaDAO();
	}
	
	public void agregarProvincia(ProvinciaDTO provinciaDTO) {
		dao.insert(provinciaDTO);
	}
	
	public void editarProvincia(ProvinciaDTO provinciaDTO) {
		dao.update(provinciaDTO);
	}

	public void borrarProvincia(ProvinciaDTO provinciaDTO) {
		dao.delete(provinciaDTO);
	}	
	
	public List<ProvinciaDTO> provinciasDisponibles() {
		return dao.readAll();
	}
}
