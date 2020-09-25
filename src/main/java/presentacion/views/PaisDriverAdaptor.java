package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.PaisDTO;
import presentacion.views.swing.PaisView;

public class PaisDriverAdaptor {

	private PaisView view = PaisView.getInstance();
	
	public void clearData() {
		view.getTableModel().setRowCount(0);
		view.getTableModel().setColumnCount(0);
		view.getTableModel().setColumnIdentifiers(view.getNombreColumnas());
	}
	
	public void setData(List<PaisDTO> dtos) {
		assert dtos != null;
		for (PaisDTO loc : dtos) {
			Object[] row = { loc.getNombre(), loc.getId() };
			view.getTableModel().addRow(row);
		}	
	}
	
	public PaisDTO getData() {
		int row = view.getTable().getSelectedRow();
		String nom = view.getTableModel().getValueAt(row, 0).toString();
		int id = Integer.parseInt(view.getTableModel().getValueAt(row, 1).toString());
		return new PaisDTO(id, nom);
	}
	
	public void open() {
		view.setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		view.getBtnSalvar().addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		view.getBtnEditar().addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		view.getBtnEliminar().addActionListener(object);
	}
}
