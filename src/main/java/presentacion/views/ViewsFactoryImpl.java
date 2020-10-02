package presentacion.views;

import business_logic.LocalidadController;
import business_logic.Observable;
import business_logic.PaisController;
import business_logic.ProvinciaController;
import business_logic.TipoController;
import business_logic.local.ConfigurationServiceImpl;
import presentacion.ConfigurationPresenter;
import presentacion.LocalidadPresenter;
import presentacion.PaisPresenter;
import presentacion.Presenter;
import presentacion.ProvinciaPresenter;
import presentacion.TiposPresenter;
import presentacion.ViewsFactory;
import presentacion.WorkbenchPresenter;

public class ViewsFactoryImpl extends ViewsFactory {

	private WorkbenchPresenter workbenchPresenter;
	
	@Override
	public Presenter makePresenter() {
		workbenchPresenter = new WorkbenchPresenter();
		
		Observable observable4 = (Observable) controllers.makeLocalidadController();
		observable4.registerObserver(workbenchPresenter);
		new LocalidadPresenter((LocalidadController) observable4);
		
		Observable observable = (Observable) controllers.makeTipoController();
		observable.registerObserver(workbenchPresenter);
		new TiposPresenter((TipoController) observable);
				
		Observable observable2 = (Observable) controllers.makePaisController();
		observable2.registerObserver(workbenchPresenter);
		new PaisPresenter((PaisController) observable2);
		
		Observable observable3 = (Observable) controllers.makeProvinciaController();
		observable3.registerObserver(workbenchPresenter);
		new ProvinciaPresenter((ProvinciaController) observable3);

		new ConfigurationPresenter(new ConfigurationServiceImpl());
		return this.workbenchPresenter;
	}
}
