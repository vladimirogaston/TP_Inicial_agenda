package main;

import presentacion.WorkbenchPresenter;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import business_logic.ControllersFactory;
import business_logic.local.ControllersFactoryImpl;
import presentacion.LocalidadPresenter;
import presentacion.PaisPresenter;
import presentacion.ProvinciaPresenter;
import presentacion.TiposPresenter;
import presentacion.views.LocalidadDriverAdaptor;
import presentacion.views.PaisDriverAdaptor;
import presentacion.views.PersonaDriverAdaptor;
import presentacion.views.TiposDriverAdaptor;
import presentacion.views.WorkbenchDriverAdaptor;
import presentacion.views.swing.ProvinciaView;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;

public class AgendaApp {

	WorkbenchPresenter starter;
	
	public AgendaApp domainLogic() {
		DaosFactory.setFactory(new DaosFactoryImpl());
		ControllersFactory.setDaosFactory(DaosFactory.getFactory());
		ControllersFactory.setFactory(new ControllersFactoryImpl());
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

	public AgendaApp injectDependencies() {
		starter = new WorkbenchPresenter(new WorkbenchDriverAdaptor(), new PersonaDriverAdaptor());
		new LocalidadPresenter(new LocalidadDriverAdaptor(), ControllersFactory.getFactory().getLocalidadController());
		new TiposPresenter(new TiposDriverAdaptor(), ControllersFactory.getFactory().getTipoController());
		new PaisPresenter(new PaisDriverAdaptor(), ControllersFactory.getFactory().getPaisController());
		new ProvinciaPresenter(ProvinciaView.getInstance(), ControllersFactory.getFactory().getProvinciaController());
		return this;
	}
		
	public void init() {
		starter.onInit();
	}
	
	public static void main(String[] args) {
		new AgendaApp()
			.setUpLookAndFeel()
			.domainLogic()
			.injectDependencies()
			.init();
	}
}
