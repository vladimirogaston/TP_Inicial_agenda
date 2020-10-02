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
	
	@Override
	public PersonaController makePersonaController() {
		if(personaController == null) {
			personaController = new PersonaControllerImpl(daos);
		}
		return personaController;
	}

	@Override
	public LocalidadController makeLocalidadController() {
		if(localidadController == null) {
			localidadController = new ObservableLocalidadController(new LocalidadControllerImpl(daos.createLocalidadDAO()));
		}
		return localidadController;
	}

	@Override
	public ProvinciaController makeProvinciaController() {
		if(provinciaController == null) {
			provinciaController = new ObservableProvinciaController(new ProvinciaControllerImpl(daos.createProvinciaDAO()));
		}
		return provinciaController;
	}

	@Override
	public PaisController makePaisController() {
		if(paisController == null) {
			paisController = new ObservablePaisController(new PaisControllerImpl(daos.createPaisDAO()));
		}
		return paisController;
	}

	@Override
	public TipoController makeTipoController() {
		if(tipoController == null) {
			tipoController =  new ObservableTipoController(new TipoControllerImpl(daos.createTipoContactoDAO()));
		}
		return tipoController;
	}
}
