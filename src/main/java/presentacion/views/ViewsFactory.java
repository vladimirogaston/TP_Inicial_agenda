package presentacion.views;

public abstract class ViewsFactory {

	private static ViewsFactory concreteFactory;
	
	public static void setFactory(ViewsFactory factory) {
		assert factory != null;
		concreteFactory = factory;
	}
	
	public static ViewsFactory getFactory() {
		return concreteFactory;
	}
	
	public abstract PersonaView makePersonaView();
	
	public abstract PaisView makePaisView();

	public abstract LocalidadView makeLocalidadView();
	
	public abstract TiposView makeTiposView();
}
