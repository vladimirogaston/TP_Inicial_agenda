package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.PersonaController;
import business_logic.TipoController;
import business_logic.ControllersFactoryImpl;
import business_logic.DatabaseException;
import dto.TipoContactoDTO;
import presentacion.views.TiposView;
import presentacion.views.WorkbenchView;

public class TiposPresenter {

	TiposView vista;
	TipoController controller = ControllersFactoryImpl.getInstance().getTipoController();

	public TiposPresenter(TiposView vista) {
		super();
		this.vista = vista;
		WorkbenchView.getInstance().getMntmNewMenuItemTipos().addActionListener((a)->inicializar(a));
		vista.getBtnNewButtonSalvar().addActionListener((a) -> {
			onSalvar(a);
		});
		vista.getBtnNewButtonEliminar().addActionListener((a)->onBorrar(a));
		vista.getBtnNewButtonEditar().addActionListener((a)->onEditar(a));
	}
	
	void onEditar(ActionEvent action) {
		if(vista.getTable().getSelectedRowCount() == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 1).toString());
			final String locNom = vista.getTableModel().getValueAt(row, 0).toString();
			String nuevoNom = vista.displayForm();
			if(nuevoNom != null && !nuevoNom.matches(locNom) && !nuevoNom.trim().isEmpty()) {
				TipoContactoDTO dto = new TipoContactoDTO(locID, nuevoNom);
				try {
					controller.editarTipoDeContacto(dto);
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
			TipoContactoDTO dto = new TipoContactoDTO(nom);
			try {
				controller.agregarTipoDeContacto(dto);
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
				controller.borrarTipoDeContacto(new TipoContactoDTO(tcID, tcNombre));
				vaciarTabla();
				llenarTabla();
			} catch (DatabaseException e) {
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
		List<TipoContactoDTO> localidades = controller.tiposDisponibles();
		for (TipoContactoDTO loc : localidades) {
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
