package modelo;

import java.util.List;

import dto.TipoContactoDTO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoController {
	
	TipoContactoDAO dao;
	
	public TipoContactoController(TipoContactoDAO dao) {
		this.dao = dao;
	}
	
	public List<TipoContactoDTO> readAll() {
		return dao.readAll();
	}
}
