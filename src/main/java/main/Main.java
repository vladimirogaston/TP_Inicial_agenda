package main;

import presentacion.WorkbenchPresenter;
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

public class Main {

	WorkbenchPresenter controladorContactos;
	LocalidadPresenter controladorLocalidades;
	TiposPresenter controladorTipos;
	PaisPresenter controladorPais;
	ProvinciaPresenter controladorProvincia;
	
	public Main() {		
		DaosFactory.setFactory(new DaosFactoryImpl());
		ControllersFactory.setDaosFactory(DaosFactory.getFactory());
		ControllersFactory.setFactory(new ControllersFactoryImpl());
		
		controladorContactos = new WorkbenchPresenter(new WorkbenchDriverAdaptor(), new PersonaDriverAdaptor());
		controladorLocalidades = new LocalidadPresenter(new LocalidadDriverAdaptor(), ControllersFactory.getFactory().getLocalidadController());
		controladorTipos = new TiposPresenter(new TiposDriverAdaptor(), ControllersFactory.getFactory().getTipoController());
		controladorPais = new PaisPresenter(new PaisDriverAdaptor(), ControllersFactory.getFactory().getPaisController());
		controladorProvincia = new ProvinciaPresenter(ProvinciaView.getInstance(), ControllersFactory.getFactory().getProvinciaController());
	}
	
	public void init() {
		controladorContactos.onInit();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
