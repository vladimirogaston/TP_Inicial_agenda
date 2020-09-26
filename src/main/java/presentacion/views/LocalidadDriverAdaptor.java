package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.LocalidadDTO;
import presentacion.views.swing.LocalidadesView;

public class LocalidadDriverAdaptor {
	
	private LocalidadesView view = LocalidadesView.getInstance();
	
	public void clearData() {
		view.getTableModel().setRowCount(0);
		view.getTableModel().setColumnCount(0);
		view.getTableModel().setColumnIdentifiers(view.getNombreColumnas());
	}
	
	public void setData(List<LocalidadDTO> localidades) {
		assert localidades != null;
		for (LocalidadDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getProvincia(), loc.getId() };
			view.getTableModel().addRow(row);
		}	
	}
	
	public LocalidadDTO getData() {
		if(view.getTable().getSelectedColumnCount() != 1) {
			return null;
		}
		int row = view.getTable().getSelectedRow();
		String locNom = view.getTableModel().getValueAt(row, 0).toString();
		String provNom = view.getTableModel().getValueAt(row, 1).toString();
		int locID = Integer.parseInt(view.getTableModel().getValueAt(row, 2).toString());
		return new LocalidadDTO(locID, locNom, provNom);
	}
	
	public void open() {
		view.setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		view.getBtnSalvar().addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		view.getButtonEditar().addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		view.getBtnNewButtonEliminar().addActionListener(object);
	}
}
