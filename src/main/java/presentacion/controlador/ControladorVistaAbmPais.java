package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.PaisDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmPais;

public class ControladorVistaAbmPais {

	private VistaAbmPais vista;
	private Agenda agenda;

	public ControladorVistaAbmPais(VistaAbmPais vista, Agenda agenda) {
		super();
		this.vista = vista;
		this.agenda = agenda;
		vista.getTable().getColumn("ID").setPreferredWidth(0);
		Vista.getInstance().getMntmNewMenuItemPaises().addActionListener((a) -> inicializar(a));
		vista.getBtnSalvar().addActionListener((a) -> onSalvar(a));
		vista.getBtnEditar().addActionListener((a) -> onEditar(a));
		vista.getBtnEliminar().addActionListener((a) -> onEliminar(a));
	}

	void inicializar(ActionEvent action) {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);
	}

	void vaciarTabla() {
		vista.getTableModel().setRowCount(0);
		vista.getTableModel().setColumnCount(0);
		vista.getTableModel().setColumnIdentifiers(vista.getNombreColumnas());
	}

	void llenarTabla() {
		List<PaisDTO> paises = agenda.paisesDisponibles();
		for (PaisDTO pais : paises) {
			Object[] row = { pais.getNombre(), pais.getId() };
			vista.getTableModel().addRow(row);
		}
	}

	void onSalvar(ActionEvent action) {
		String nombre = vista.displayForm();
		if (nombre != null && !nombre.trim().isEmpty()) {
			PaisDTO paisDTO = new PaisDTO(nombre);
			try {
				agenda.agregarPais(paisDTO);
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
				vista.showMessage(e.getMessage());
			}
		}
	}

	void onEditar(ActionEvent action) {
		if(vista.getTable().getSelectedRowCount() == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int paisID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String paisNombre = vista.getTableModel().getValueAt(row, 0).toString();
			String nuevoNombre = vista.displayForm();
			if(nuevoNombre != null && !nuevoNombre.matches(paisNombre) && !nuevoNombre.trim().isEmpty()) {
				PaisDTO paisDTO = new PaisDTO(paisID, nuevoNombre);
				try {
					agenda.editarPais(paisDTO);
				} catch(DatabaseException e) {
					vista.showMessage(e.getMessage());
				} finally {
					vaciarTabla();
					llenarTabla();
				} 
			}
		}
	}
	
	void onEliminar(ActionEvent action) {
		int selectedRows = vista.getTable().getSelectedRowCount();
		if(selectedRows == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int paisID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String paisNombre = vista.getTableModel().getValueAt(row, 0).toString();
			try {
				agenda.borrarPais(new PaisDTO(paisID, paisNombre));
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
				//vista.getTable().setSelectionBackground(Color.RED);
				vista.showMessage(e.getMessage());
			}
		}
	}

	public VistaAbmPais getVista() {
		return vista;
	}

	public Agenda getAgenda() {
		return agenda;
	}
}
