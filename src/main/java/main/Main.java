package main;

import dto.PersonaDTO;
import persistencia.dao.mysql.PersonaDAOSQL;


public class Main 
{

	public static void main(String[] args) 
	{
		PersonaDTO dto = PersonaDTO.makeTestDto();
		dto.setIdPersona(2);
		dto.setNombre("xxxxx");
		new PersonaDAOSQL().update(dto);
		//Vista vista = new Vista();
		//Agenda modelo = new Agenda(new DAOSQLFactory());
		//Controlador controlador = new Controlador(vista, modelo);
		//controlador.inicializar();
	}
}
