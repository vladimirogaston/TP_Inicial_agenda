package business_logic;

import java.util.List;

import dto.LocalidadDTO;
import repositories.DaosFactory;
import repositories.LocalidadDao;

public class LocalidadController {

	private LocalidadDao dao;
	
	public LocalidadController() {
		dao = DaosFactory.getFactory().createLocalidadDAO();
	}
	
	public void borrarLocalidad(LocalidadDTO localidadDTO) {
		dao.delete(localidadDTO);
	}
	
	public void agregarLocalidad(LocalidadDTO localidadDTO) {
		dao.insert(localidadDTO);
	}
	
	public void editarLocalidad(LocalidadDTO localidadDTO) {
		dao.update(localidadDTO);
	}	
	
	public List<LocalidadDTO> localidadesDisponibles() {
		return dao.readAll();
	}
	
	public List<LocalidadDTO> localidadPorProvincia(String provincia) {
		return dao.readPorProvincia(provincia);
	}
}
