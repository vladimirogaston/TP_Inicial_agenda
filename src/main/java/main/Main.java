package main;

import presentacion.WorkbenchPresenter;
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
		controladorContactos = new WorkbenchPresenter(new WorkbenchDriverAdaptor(), new PersonaDriverAdaptor());
		controladorLocalidades = new LocalidadPresenter(new LocalidadDriverAdaptor());
		controladorTipos = new TiposPresenter(new TiposDriverAdaptor());
		controladorPais = new PaisPresenter(new PaisDriverAdaptor());
		controladorProvincia = new ProvinciaPresenter(ProvinciaView.getInstance());
	}
	
	public void init() {
		controladorContactos.onInit();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
