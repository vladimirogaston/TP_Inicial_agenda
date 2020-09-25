package main;

import presentacion.WorkbenchPresenter;
import presentacion.LocalidadPresenter;
import presentacion.PaisPresenter;
import presentacion.ProvinciaPresenter;
import presentacion.TiposPresenter;
import presentacion.views.LocalidadDriverAdaptor;
import presentacion.views.PaisView;
import presentacion.views.PersonaDriverAdaptor;
import presentacion.views.ProvinciaView;
import presentacion.views.TiposView;
import presentacion.views.WorkbenchDriverAdaptor;
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
		controladorTipos = new TiposPresenter(TiposView.getInstance());
		controladorPais = new PaisPresenter(PaisView.getInstance());
		controladorProvincia = new ProvinciaPresenter(ProvinciaView.getInstance());
	}
	
	public void init() {
		controladorContactos.onInit();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
