package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.PersonaController;
import business_logic.ControllersFactoryImpl;
import business_logic.DatabaseException;
import business_logic.PaisController;
import dto.PaisDTO;
import presentacion.views.PaisView;
import presentacion.views.WorkbenchView;

public class PaisPresenter {

	private PaisView vista;
	private PaisController controller = ControllersFactoryImpl.getInstance().getPaisController();

	public PaisPresenter(PaisView vista) {
		super();
		this.vista = vista;
		vista.getTable().getColumn("ID").setPreferredWidth(0);
		WorkbenchView.getInstance().getMntmNewMenuItemPaises().addActionListener((a) -> inicializar(a));
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
		List<PaisDTO> paises = controller.paisesDisponibles();
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
				controller.agregarPais(paisDTO);
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
					controller.editarPais(paisDTO);
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
				controller.borrarPais(new PaisDTO(paisID, paisNombre));
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
				//vista.getTable().setSelectionBackground(Color.RED);
				vista.showMessage(e.getMessage());
			}
		}
	}
}
