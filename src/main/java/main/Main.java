package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorVistaAbmLocalidades;
import presentacion.controlador.ControladorVistaAbmPais;
import presentacion.controlador.ControladorVistaAbmProvincia;
import presentacion.controlador.ControladorVistaAbmTiposContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmLocalidades;
import presentacion.vista.VistaAbmPais;
import presentacion.vista.VistaAbmProvincia;
import presentacion.vista.VistaAbmTiposDeContacto;

public class Main {

	Controlador controladorContactos;
	ControladorVistaAbmLocalidades controladorLocalidades;
	ControladorVistaAbmTiposContacto controladorTipos;
	ControladorVistaAbmPais controladorPais;
	ControladorVistaAbmProvincia controladorProvincia;
	
	public Main() {		
		Agenda modelo = new Agenda(new DAOSQLFactory());
		controladorContactos = new Controlador(Vista.getInstance(), modelo);
		controladorLocalidades = new ControladorVistaAbmLocalidades(VistaAbmLocalidades.getInstance(), modelo);
		controladorTipos = new ControladorVistaAbmTiposContacto(VistaAbmTiposDeContacto.getInstance(), modelo);
		controladorPais = new ControladorVistaAbmPais(VistaAbmPais.getInstance(), modelo);
		controladorProvincia = new ControladorVistaAbmProvincia(VistaAbmProvincia.getInstance(), modelo);
	}
	
	public void init() {
		controladorContactos.inicializar();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
