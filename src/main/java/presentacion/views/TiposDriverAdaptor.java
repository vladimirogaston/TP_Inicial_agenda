package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.TipoContactoDTO;
import presentacion.views.swing.TiposView;

public class TiposDriverAdaptor {
	
	private TiposView view = TiposView.getInstance();
	
	public void clearData() {
		view.getTableModel().setRowCount(0);
		view.getTableModel().setColumnCount(0);
		view.getTableModel().setColumnIdentifiers(view.getNombreColumnas());
	}
	
	public void setData(List<TipoContactoDTO> dtos) {
		assert dtos != null;
		for (TipoContactoDTO loc : dtos) {
			Object[] row = { loc.getNombre(), loc.getId() };
			view.getTableModel().addRow(row);
		}	
	}
	
	public TipoContactoDTO getData() {
		int rows = view.getTable().getSelectedRowCount();
		if(rows != 1) return null;
		int row = view.getTable().getSelectedRow();
		String nom = view.getTableModel().getValueAt(row, 0).toString();
		Object obj = view.getTableModel().getValueAt(row, 1);
		Integer id = null;
		if(obj != null) id = Integer.parseInt(view.getTableModel().getValueAt(row, 1).toString());
		return new TipoContactoDTO(id, nom);
	}
	
	public void open() {
		view.setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		view.getBtnNewButtonSalvar().addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		view.getBtnNewButtonEditar().addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		view.getBtnNewButtonEliminar().addActionListener(object);
	}
}
