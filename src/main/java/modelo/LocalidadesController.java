package modelo;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadesController {
	
	LocalidadDAO dao;
	
	List<LocalidadDTO> findAll() {
		return dao.readAll();
	}
}
