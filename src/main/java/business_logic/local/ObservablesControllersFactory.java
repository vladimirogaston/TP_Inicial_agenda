package business_logic.local;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.Observable;
import business_logic.ObservablesFactory;
import business_logic.PaisController;
import business_logic.ProvinciaController;
import business_logic.TipoController;

public class ObservablesControllersFactory extends ObservablesFactory {
	
	protected ControllersFactory controllersFactory;
	
	public ObservablesControllersFactory(ControllersFactory controllersFactory) {
		super();
		this.controllersFactory = controllersFactory;
	}
	
	public Observable makeObservable(Class<? extends Object> type) {
		Observable ret = null;
		if(type.equals(TipoController.class)) {
			ret = new ObservableTipoController(controllersFactory.getTipoController());
		}else if(type.equals(PaisController.class)) {
			ret = new ObservablePaisController(controllersFactory.getPaisController());
		}else if(type.equals(ProvinciaController.class)) {
			ret = new ObservableProvinciaController(controllersFactory.getProvinciaController());
		}else if(type.equals(LocalidadController.class)) {
			ret = new ObservableLocalidadController(controllersFactory.getLocalidadController());
		}
		return ret;
	}
}
