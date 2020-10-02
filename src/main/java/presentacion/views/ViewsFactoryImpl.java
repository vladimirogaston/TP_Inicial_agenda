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
		workbenchPresenter = new WorkbenchPresenter(WorkbenchView.getInstance(), PersonaView.getInstance());
		
		Observable observable4 = (Observable) controllers.makeLocalidadController();
		observable4.registerObserver(workbenchPresenter);
		new LocalidadPresenter(LocalidadView.getInstance(), (LocalidadController) observable4);
		
		Observable observable = (Observable) controllers.makeTipoController();
		observable.registerObserver(workbenchPresenter);
		new TiposPresenter(TiposView.getInstance(), (TipoController) observable);
				
		Observable observable2 = (Observable) controllers.makePaisController();
		observable2.registerObserver(workbenchPresenter);
		new PaisPresenter(PaisView.getInstance(), (PaisController) observable2);
		
		Observable observable3 = (Observable) controllers.makeProvinciaController();
		observable3.registerObserver(workbenchPresenter);
		new ProvinciaPresenter(ProvinciaView.getInstance(), (ProvinciaController) observable3);

		ConfigurationPresenter configPresenter = new ConfigurationPresenter(new ConfigurationView(), new ConfigurationServiceImpl());
		configPresenter.setRootViewAction(WorkbenchView.getInstance());
		return this.workbenchPresenter;
	}
}
