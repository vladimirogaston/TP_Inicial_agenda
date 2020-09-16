package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorVistaAbmLocalidades;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmLocalidades;


public class Main 
{

	public static void main(String[] args) {
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		
		VistaAbmLocalidades vistalocalidades =  new VistaAbmLocalidades();
		ControladorVistaAbmLocalidades controladorlocalidades = new ControladorVistaAbmLocalidades(vistalocalidades, modelo);
		controladorlocalidades.inicializar();
	}
}
