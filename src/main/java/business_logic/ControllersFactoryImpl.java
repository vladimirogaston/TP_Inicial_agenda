package business_logic;

public class ControllersFactoryImpl {

	private PersonaController personaController;
	private LocalidadController localidadController;
	private ProvinciaController provinciaController;
	private PaisController paisController;
	private TipoController tipoController;
	private static ControllersFactoryImpl instance;
	
	private ControllersFactoryImpl() {
		personaController = new PersonaController();
		localidadController = new LocalidadController();
		provinciaController = new ProvinciaController();
		paisController = new PaisController();
		tipoController = new TipoController();
	}
	
	public static ControllersFactoryImpl getInstance() {
		if(instance == null) {
			instance = new ControllersFactoryImpl();
		}
		return instance;
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
