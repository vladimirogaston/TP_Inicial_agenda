package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VistaAbmTiposDeContacto;

public class ControladorVistaAbmTiposContacto {

	VistaAbmTiposDeContacto vista;
	Agenda agenda;

	public ControladorVistaAbmTiposContacto(VistaAbmTiposDeContacto vista, Agenda agenda) {
		super();
		this.vista = vista;
		this.agenda = agenda;
		vista.getBtnNewButtonSalvar().addActionListener((a) -> {
			onSalvar(a);
		});
		vista.getBtnNewButtonEliminar().addActionListener((a) -> {
			onBorrar(a);
		});
	}

	void onSalvar(ActionEvent action) {
	}

	void onBorrar(ActionEvent action) {

	}
	
	void onEditar(ActionEvent action) {
		
	}
	
	void vaciarTabla() {
		vista.getTableModel().setRowCount(0);
		vista.getTableModel().setColumnCount(0);
		vista.getTableModel().setColumnIdentifiers(vista.getNombreColumnas());
	}

	void llenarTabla() {
		List<TipoContactoDTO> localidades = agenda.tiposDisponibles();
		for (TipoContactoDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getId() };
			vista.getTableModel().addRow(row);
		}
	}

	public void inicializar() {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);
	}
}
