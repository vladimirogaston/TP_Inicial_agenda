package business_logic.local;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.PaisController;
import business_logic.PersonaController;
import business_logic.PersonaControllerImpl;
import business_logic.ProvinciaController;
import business_logic.TipoController;

public class ControllersFactoryImpl extends ControllersFactory {

	public ControllersFactoryImpl() {
		super();
		if(daos == null) {
			throw new IllegalArgumentException("Daos factory no debe ser null.");
		}
	}
	
	public PersonaController getPersonaController() {
		return new PersonaControllerImpl(daos);
	}

	public LocalidadController getLocalidadController() {
		return new LocalidadControllerImpl(daos.createLocalidadDAO());
	}

	public ProvinciaController getProvinciaController() {
		return new ProvinciaControllerImpl(daos.createProvinciaDAO());
	}

	public PaisController getPaisController() {
		return new PaisControllerImpl(daos.createPaisDAO());
	}

	public TipoController getTipoController() {
		return new TipoControllerImpl(daos.createTipoContactoDAO());
	}
}
