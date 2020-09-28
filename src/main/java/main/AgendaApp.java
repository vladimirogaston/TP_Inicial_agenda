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
import business_logic.local.ControllersFactoryImpl;
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
		ControllersFactory.setDaosFactory
		(DaosFactory.getFactory());
		ControllersFactory.setFactory(new ControllersFactoryImpl());
		return this;
	}
	
	public AgendaApp presentationLogic() {
		ViewsFactory.setFactory(new ViewsFactoryImpl());
		starter = new WorkbenchPresenter(WorkbenchViewImpl.getInstance(), PersonaViewImpl.getInstance());
		new LocalidadPresenter(ViewsFactory.getFactory().makeLocalidadView(), ControllersFactory.getFactory().getLocalidadController());
		new TiposPresenter(ViewsFactory.getFactory().makeTiposView(), ControllersFactory.getFactory().getTipoController());
		new PaisPresenter(ViewsFactory.getFactory().makePaisView(), ControllersFactory.getFactory().getPaisController());
		new ProvinciaPresenter(ProvinciaViewImpl.getInstance(), ControllersFactory.getFactory().getProvinciaController());	
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