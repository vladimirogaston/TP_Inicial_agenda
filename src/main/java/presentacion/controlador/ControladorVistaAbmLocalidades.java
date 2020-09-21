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
		vista.getTable().getColumn("ID").setPreferredWidth(0);		
		Vista.getInstance().getMntmNewMenuItemLocalidades().addActionListener((a) -> inicializar(a));
		vista.getBtnSalvar().addActionListener((a)->onSalvar(a));
		vista.getButtonEditar().addActionListener((a)->onEditar(a));
		vista.getBtnNewButtonEliminar().addActionListener((a)->onBorrar(a));
	}
	
	void onEditar(ActionEvent action) {
		if(vista.getTable().getSelectedRowCount() == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String locNom = vista.getTableModel().getValueAt(row, 0).toString();
			String nuevoNom = vista.displayForm();
			if(nuevoNom != null && !nuevoNom.matches(locNom) && !nuevoNom.trim().isEmpty()) {
				LocalidadDTO dto = new LocalidadDTO(locID, nuevoNom);
				try {
					agenda.editarLocalidad(dto);
				} catch(DatabaseException e) {
					vista.showMessage(e.getMessage());
				} finally {
					vaciarTabla();
					llenarTabla();
				} 
			}
		}
	}

	void onSalvar(ActionEvent action) {
		String nom = vista.displayForm();
		if(nom != null && !nom.trim().isEmpty()) {
			LocalidadDTO dto = new LocalidadDTO(nom);
			try {
				agenda.agregarLocalidad(dto);
				vaciarTabla();
				llenarTabla();
			} catch(DatabaseException e) {
				vista.showMessage(e.getMessage());
			}
		}
	}

	void onBorrar(ActionEvent action) {
		int selectedRows = vista.getTable().getSelectedRowCount();
		if (selectedRows == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String locNombre = vista.getTableModel().getValueAt(row, 0).toString();
			try {
				agenda.borrarLocalidad(new LocalidadDTO(locID, locNombre));
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
				//vista.getTable().setSelectionBackground(Color.RED);
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

	public void inicializar(ActionEvent action) {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);
	}
}
