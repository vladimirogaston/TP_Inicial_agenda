package main;

import presentacion.WorkbenchPresenter;
import presentacion.views.ViewsFactory;
import presentacion.views.WorkbenchViewImpl;
import presentacion.views.swing.PersonaViewImpl;
import presentacion.views.swing.ProvinciaViewImpl;
import presentacion.views.swing.ViewsFactoryImpl;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.ObservablesFactory;
import business_logic.PaisController;
import business_logic.ProvinciaController;
import business_logic.Observable;
import business_logic.TipoController;
import business_logic.local.ControllersFactoryImpl;
import business_logic.local.ObservablesControllersFactory;
import presentacion.LocalidadPresenter;
import presentacion.PaisPresenter;
import presentacion.ProvinciaPresenter;
import presentacion.TiposPresenter;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;

public class AgendaApp {

	WorkbenchPresenter starter;
	
	public AgendaApp domainLogic() {
		DaosFactory.setFactory(new DaosFactoryImpl());
		ControllersFactory.setDaosFactory(DaosFactory.getFactory());
		ControllersFactory.setFactory(new ControllersFactoryImpl());
		ObservablesFactory.setFactory(new ObservablesControllersFactory(ControllersFactory.getFactory()));
		return this;
	}
	
	// TODO crear una clase para inicializar la capa de presentacion de forma flexible
	// no puede sobrevivir
	public AgendaApp presentationLogic() {
		ViewsFactory.setFactory(new ViewsFactoryImpl());
		starter = new WorkbenchPresenter(WorkbenchViewImpl.getInstance(), PersonaViewImpl.getInstance());
		
		Observable observable4 = ObservablesFactory.getFactory().makeObservable(LocalidadController.class);
		observable4.registerObserver(starter);
		new LocalidadPresenter(ViewsFactory.getFactory().makeLocalidadView(), (LocalidadController) observable4);
		
		//registra observer
		Observable observable = ObservablesFactory.getFactory().makeObservable(TipoController.class);
		observable.registerObserver(starter);
		new TiposPresenter(ViewsFactory.getFactory().makeTiposView(), (TipoController) observable);
				
		Observable observable2 = ObservablesFactory.getFactory().makeObservable(PaisController.class);
		observable2.registerObserver(starter);
		new PaisPresenter(ViewsFactory.getFactory().makePaisView(), (PaisController) observable2);
		
		Observable observable3 = ObservablesFactory.getFactory().makeObservable(ProvinciaController.class);
		observable3.registerObserver(starter);
		new ProvinciaPresenter(ProvinciaViewImpl.getInstance(), (ProvinciaController) observable3);	
		return this;
	}

	public AgendaApp setUpLookAndFeel() {
		try {
	        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (Exception e) {
	        try {
	            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        } catch (Exception ex) {
	        }
	    }
		return this;
	}
		
	public void init() {
		starter.onInit();
	}

	public static void main(String[] args) {
		new AgendaApp()
			.setUpLookAndFeel()
			.domainLogic()
			.presentationLogic()
			.init();	
	}
}