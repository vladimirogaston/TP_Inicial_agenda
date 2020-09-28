package presentacion.views.swing;

import presentacion.views.LocalidadView;
import presentacion.views.PaisView;
import presentacion.views.PersonaView;
import presentacion.views.TiposView;
import presentacion.views.ViewsFactory;

public class ViewsFactoryImpl extends ViewsFactory {

	@Override
	public PersonaView makePersonaView() {
		return PersonaViewImpl.getInstance();
	}

	@Override
	public PaisView makePaisView() {
		return PaisViewImpl.getInstance();
	}

	@Override
	public LocalidadView makeLocalidadView() {
		return LocalidadesViewImpl.getInstance();
	}

	@Override
	public TiposView makeTiposView() {
		return TiposViewImpl.getInstance();
	}
}
