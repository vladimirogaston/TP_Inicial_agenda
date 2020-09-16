package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.LocalidadDTO;
import modelo.Agenda;
import presentacion.vista.VistaAbmLocalidades;

public class ControladorVistaAbmLocalidades {
	
	VistaAbmLocalidades vista;
	Agenda agenda;
	
	public ControladorVistaAbmLocalidades(VistaAbmLocalidades vista, Agenda agenda) {
		super();
		this.vista = vista;
		this.agenda = agenda;
		vista.getBtnNewButtonSalvar().addActionListener((a)->{onSalvar(a);});
	}
	
	void onSalvar(ActionEvent action) {
	}

	void vaciarTabla() {
		vista.getTableModel().setRowCount(0);
		vista.getTableModel().setColumnCount(0);
		vista.getTableModel().setColumnIdentifiers(vista.getNombreColumnas());
	}
	
	void llenarTabla() {
		List<LocalidadDTO> localidades = agenda.localidadesDisponibles();
		for(LocalidadDTO loc : localidades) {
			Object [] row = {loc.getNombre(), loc.getId()};
			vista.getTableModel().addRow(row);
		}
	}
	
	public void inicializar() {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);		
	}	
}
