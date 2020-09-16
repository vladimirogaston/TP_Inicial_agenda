package modelo;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadesController {
	
	LocalidadDAO dao;
	
	public LocalidadesController(LocalidadDAO dao) {
		super();
		this.dao = dao;
	}

	public List<LocalidadDTO> findAll() {
		return dao.readAll();
	}
}
