package business_logic;

import repositories.DaosFactory;

public abstract class ControllersFactory {

	private static ControllersFactory concreteFactory;
	protected static DaosFactory daos;
		
	public static void setFactory(ControllersFactory factory) {
		assert factory != null;
		concreteFactory = factory;
	}	
		
	public static ControllersFactory getFactory() {
		assert concreteFactory != null;
		return concreteFactory;
	}
	
	public static void setDaosFactory(DaosFactory daosf) {
		assert daosf != null;
		daos = daosf;
	}
	
	public abstract PersonaController getPersonaController();

	public abstract LocalidadController getLocalidadController();

	public abstract ProvinciaController getProvinciaController();

	public abstract PaisController getPaisController();

	public abstract TipoController getTipoController();
}
