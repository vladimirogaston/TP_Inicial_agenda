package repositories;

public abstract class DaosFactory {

	private static DaosFactory concreteFactory;
		
	public static void setFactory(DaosFactory factory) {
		concreteFactory = factory;
	}
	
	public static DaosFactory getFactory() {
		if(concreteFactory == null) throw new RuntimeException("Inject concrete factory first.");
		return concreteFactory;
	}
	
	public abstract PersonaDao createPersonaDAO();

	public abstract LocalidadDao createLocalidadDAO();

	public abstract TipoContactoDao createTipoContactoDAO();

	public abstract ProvinciaDao createProvinciaDAO();

	public abstract PaisDao createPaisDAO();
}
