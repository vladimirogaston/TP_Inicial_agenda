package business_logic;

import java.util.LinkedList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import repositories.DaosFactory;
import repositories.PersonaDao;
import repositories.jdbc.Persona;

public class PersonaController {
	
	private PersonaDao dao;
	
	public PersonaController() {
		dao = DaosFactory.getFactory().createPersonaDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona) {
		dao.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		dao.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		LinkedList<PersonaDTO> lst = new LinkedList<>();
		for(Persona entity : dao.readAllEntities()) {
			TipoContactoDTO dto = DaosFactory.getFactory().createTipoContactoDAO().readByID(entity.getTipoContactoID());
			String tc =  dto != null ? dto.getNombre() : null;
			LocalidadDTO locdto = DaosFactory.getFactory().createLocalidadDAO().readByID(entity.getLocalidadID());
			String loc =  locdto != null ? locdto.getNombre() : null;
			ProvinciaDTO provdto = DaosFactory.getFactory().createProvinciaDAO().readByID(entity.getProvinciaID());
			String pro = provdto != null ? provdto.getNombre() : null;
			PaisDTO paisdto = DaosFactory.getFactory().createPaisDAO().readByID(entity.getPaisID());
			String pais = paisdto != null ? paisdto.getNombre() : null;
			lst.add(new PersonaDTO
			.Builder(entity.getNombre(), entity.getTelefono())
			.email(entity.getEmail())
			.id(entity.getIdPersona())
			.fechaNacimiento(entity.getFechaNacimiento())
			.tipoContacto(tc)
			.calle(entity.getCalle())
			.altura(entity.getAltura())
			.piso(entity.getPiso())
			.dpto(entity.getDpto())
			.localidad(loc)
			.provincia(pro)
			.codigoPostal(entity.getCodigoPostal() == 0 ? "" : entity.getCodigoPostal().toString())
			.equipoFutbol(entity.getEquipoFutbol())
			.pais(pais)
			.build());
		}
		return lst;
	}

	public void editar(PersonaDTO nuevaPersona) {
		dao.update(nuevaPersona);
	}
}