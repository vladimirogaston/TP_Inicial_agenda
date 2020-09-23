package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	
	private PersonaDAO persona;
	LocalidadDAO localidades;
	TipoContactoDAO tipos;
	PaisDAO paises;
	ProvinciaDAO provincias;

	public List<LocalidadDTO> localidadPorProvincia(String provincia) {
		return localidades.readPorProvincia(provincia);
	}
	
	public List<ProvinciaDTO> provinciasDisponibles() {
		return provincias.readAll();
	}
	
	public List<PaisDTO> paisesDisponibles() {
		return paises.readAll();
	}
	
	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		localidades = metodo_persistencia.createLocalidadDAO();
		tipos = metodo_persistencia.createTipoContactoDAO();
		provincias = metodo_persistencia.createProvinciaDAO();
		paises = metodo_persistencia.createPaisDAO();
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

	public void agregarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		tipos.insert(tipoContactoDTO);
	}
	
	public void borrarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		tipos.delete(tipoContactoDTO);
	}

	public void agregarLocalidad(LocalidadDTO localidadDTO) {
		this.localidades.insert(localidadDTO);
	}
	
	public void editarLocalidad(LocalidadDTO localidadDTO) {
		this.localidades.update(localidadDTO);
	}

	public void editarTipoDeContacto(TipoContactoDTO tipoContactoDTO) {
		tipos.update(tipoContactoDTO);
	}

	public void agregarPais(PaisDTO paisDTO) {
		paises.insert(paisDTO);
	}

	public void editarPais(PaisDTO paisDTO) {
		paises.update(paisDTO);
	}
}
