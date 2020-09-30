package business_logic.local;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.PaisController;
import business_logic.PersonaController;
import business_logic.ProvinciaController;
import business_logic.TipoController;

public class ControllersFactoryImpl extends ControllersFactory {

	private PersonaController personaController;
	private ProvinciaController provinciaController;
	private PaisController paisController;
	private LocalidadController localidadController;
	private TipoController tipoController;
	
	public ControllersFactoryImpl() {
		super();
		if(daos == null) {
			throw new IllegalArgumentException("Daos factory no debe ser null.");
		}
		personaController = new PersonaControllerImpl(daos);
		localidadController = new LocalidadControllerImpl(daos.createLocalidadDAO());
		provinciaController = new ProvinciaControllerImpl(daos.createProvinciaDAO());
		paisController = new PaisControllerImpl(daos.createPaisDAO());
		tipoController =  new TipoControllerImpl(daos.createTipoContactoDAO());
	}
	
	public PersonaController getPersonaController() {
		return personaController;
	}

	public LocalidadController getLocalidadController() {
		return localidadController;
	}

	public ProvinciaController getProvinciaController() {
		return provinciaController;
	}

	public PaisController getPaisController() {
		return paisController;
	}

	public TipoController getTipoController() {
		return tipoController;
	}
}
