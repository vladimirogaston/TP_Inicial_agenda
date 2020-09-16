package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	private PersonaDAO persona;
	LocalidadDAO localidades;
	TipoContactoDAO tipos;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		localidades = metodo_persistencia.createLocalidadDAO();
		tipos = metodo_persistencia.createTipoContactoDAO();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}

	public List<TipoContactoDTO> tiposDisponibles() {
		return tipos.readAll();
	}

	public List<LocalidadDTO> localidadesDisponibles() {
		return localidades.readAll();
	}

	public void editar(PersonaDTO nuevaPersona) {
		persona.update(nuevaPersona);
	}

	public void borrarLocalidad(LocalidadDTO localidadDTO) {
		localidades.delete(localidadDTO);
	}
}
