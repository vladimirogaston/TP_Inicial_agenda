package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorVistaAbmLocalidades;
import presentacion.controlador.ControladorVistaAbmTiposContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmLocalidades;
import presentacion.vista.VistaAbmTiposDeContacto;

public class Main {

	Controlador controladorContactos;
	ControladorVistaAbmLocalidades controladorLocalidades;
	ControladorVistaAbmTiposContacto controladorTipos;
	
	public Main() {
		Agenda modelo = new Agenda(new DAOSQLFactory());
		controladorContactos = new Controlador(new Vista(), modelo);
		controladorLocalidades = new ControladorVistaAbmLocalidades(new VistaAbmLocalidades(), modelo);
		controladorTipos = new ControladorVistaAbmTiposContacto(new VistaAbmTiposDeContacto(), modelo);
	}
	
	public void init() {
		controladorContactos.inicializar();
		controladorLocalidades.inicializar();
		controladorTipos.inicializar();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
