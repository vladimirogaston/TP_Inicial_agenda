package main;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
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
		controladorContactos = new Controlador(Vista.getInstance(), modelo);
		controladorLocalidades = new ControladorVistaAbmLocalidades(VistaAbmLocalidades.getInstance(), modelo);
		controladorTipos = new ControladorVistaAbmTiposContacto(VistaAbmTiposDeContacto.getInstance(), modelo);
	}
	
	public void init() {
		controladorContactos.inicializar();
	}
	
	public static void main(String[] args) {
		new Main().init();
	}
}
