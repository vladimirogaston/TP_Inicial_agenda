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
		vista.getBtnNewButtonSalvar().addActionListener((a) -> {
			onSalvar(a);
		});
		vista.getBtnNewButtonEliminar().addActionListener((a) -> {
			onBorrar(a);
		});
		vista.getBtnNewButtonEditar().addActionListener((a) -> {
			onEditar(a);
		});
	}

	void onSalvar(ActionEvent action) {
		String nombre = vista.getTextFieldNombre().getText();
		if(nombre != null && !nombre.trim().isEmpty()) {
			LocalidadDTO dto = new LocalidadDTO(nombre);
			try {
				agenda.agregarLocalidad(dto);
				vista.getTextFieldNombre().setText("");
				vaciarTabla();
				llenarTabla();
			} catch(DatabaseException e){
				vista.showMessage(e.getMessage());
			}
		}
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
	
	void onEditar(ActionEvent action) {
		String nuevoNombre = vista.getTextFieldNombre().getText();
		int selectedRows = vista.getTable().getSelectedRowCount();
		if(selectedRows == 1 && nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
			final int row = vista.getTable().getSelectedRow();
			final int idLocalidad = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(idLocalidad, nuevoNombre);
			try {
				agenda.editarLocalidad(nuevaLocalidad);
				vista.getTextFieldNombre().setText("");
				vaciarTabla();
				llenarTabla();
			} catch(DatabaseException e){
				vista.showMessage(e.getMessage());
			}
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

	public void inicializar() {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);
	}
}
