package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.LocalidadDTO;
import modelo.Agenda;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmLocalidades;

public class ControladorVistaAbmLocalidades {

	VistaAbmLocalidades vista;
	Agenda agenda;

	public ControladorVistaAbmLocalidades(VistaAbmLocalidades vista, Agenda agenda) {
		super();
		this.vista = vista;
		this.agenda = agenda;
		Vista.getInstance().getMntmNewMenuItemLocalidades().addActionListener((a)->inicializar(a));
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
		int selectedRows = vista.getTable().getSelectedRowCount();
		if(selectedRows == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String locNombre = vista.getTableModel().getValueAt(row, 0).toString();
			agenda.borrarLocalidad(new LocalidadDTO(locID, locNombre));
			vaciarTabla();
			llenarTabla();
		}
	}
	
	void vaciarTabla() {
		vista.getTableModel().setRowCount(0);
		vista.getTableModel().setColumnCount(0);
		vista.getTableModel().setColumnIdentifiers(vista.getNombreColumnas());
	}

	void llenarTabla() {
		List<LocalidadDTO> localidades = agenda.localidadesDisponibles();
		for (LocalidadDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getId() };
			vista.getTableModel().addRow(row);
		}
	}

	public void inicializar(ActionEvent action) {
		vaciarTabla();
		llenarTabla();
		vista.getTextFieldNombre().setText("");
		vista.setVisible(true);
	}
}
