package business_logic;

import java.util.List;

import dto.TipoContactoDTO;
import repositories.DaosFactory;
import repositories.TipoContactoDao;

public class TipoController {

	private TipoContactoDao dao;
	
	public TipoController() {
		 dao = DaosFactory.getFactory().createTipoContactoDAO();
	}
	
	public void agregarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		dao.insert(tipoContactoDTO);
	}
	
	public void borrarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		dao.delete(tipoContactoDTO);
	}

	public void editarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		dao.update(tipoContactoDTO);
	}
	
	public List<TipoContactoDTO> tiposDisponibles() {
		return dao.readAll();
	}
}
