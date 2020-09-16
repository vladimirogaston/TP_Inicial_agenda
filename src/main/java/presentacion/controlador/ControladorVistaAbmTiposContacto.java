package presentacion.controlador;

import java.awt.Color;
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
		String nom = vista.getTextFieldNombre().getText();
		if(nom != null && !nom.trim().isEmpty()) {
			TipoContactoDTO dto = new TipoContactoDTO(nom);
			try {
				agenda.agregarTipoDeContacto(dto);
				vista.getTextFieldNombre().setText("");
				vaciarTabla();
				llenarTabla();
			} catch(DatabaseException e) {
				vista.showMessage(e.getMessage());
			}
		}
	}

	void onBorrar(ActionEvent action) {
		int selectedRows = vista.getTable().getSelectedRowCount();
		if(selectedRows == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int tcID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String tcNombre = vista.getTableModel().getValueAt(row, 0).toString();
			try {
				agenda.borrarTipoDeContacto(new TipoContactoDTO(tcID, tcNombre));
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
				vista.getTable().setSelectionBackground(Color.RED);
				vista.showMessage(e.getMessage());
			}
		}
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
