package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.ProvinciaController;
import business_logic.exceptions.ForbiddenException;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import presentacion.views.ProvinciaView;
import presentacion.views.WorkbenchView;

public class ProvinciaPresenter {

	private ProvinciaView vista;
	private ProvinciaController controller;
	
	public ProvinciaPresenter(ProvinciaView vista, ProvinciaController controller) {
		super();
		this.vista = vista;
		this.controller = controller;
		vista.getTable().getColumn("ID").setPreferredWidth(0);
		WorkbenchView.getInstance().getMntmNewMenuItemProvincias().addActionListener((a) -> inicializar(a));
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
		List<ProvinciaDTO> provincias = controller.readAll();
		for (ProvinciaDTO provincia : provincias) {
			Object[] row = {provincia.getNombre(), provincia.getPais(), provincia.getId() };
			vista.getTableModel().addRow(row);
		}
	}
	
	void onSalvar(ActionEvent action) {
		String [] paises = obtenerNombrePaises();
		Object [] obj = vista.displayForm(paises);
		String nombre = obj[0].toString();
		String pais = obj[1] == null ? null : obj[1].toString();
		if(nombre != null && !nombre.trim().isEmpty()) {
			ProvinciaDTO provinciaDTO = new ProvinciaDTO(null, nombre, pais);
			try {
				controller.save(provinciaDTO);
				vaciarTabla();
				llenarTabla();
			} catch(ForbiddenException e) {
				vista.showMessage(e.getMessage());
			}
		}
	}
	
	void onEditar(ActionEvent action) {
		if(vista.getTable().getSelectedRowCount() == 1) {
			int row = vista.getTable().getSelectedRow();
			String provNom = vista.getTableModel().getValueAt(row, 0).toString();
			String paisNom = vista.getTableModel().getValueAt(row, 1).toString();
			int provID = Integer.parseInt(vista.getTableModel().getValueAt(row, 2).toString());
			String [] paises = obtenerNombrePaises();
			if(paises != null) {
				Object [] obj = vista.displayForm(paises, provNom, paisNom);	
				ProvinciaDTO provinciaDTO = new ProvinciaDTO(provID, obj[0].toString(), obj[1].toString());
				
				if(provinciaDTO.getNombre() != null && !provinciaDTO.getNombre().trim().isEmpty()) {
					try {
						controller.update(provinciaDTO);
						vaciarTabla();
						llenarTabla();
					} catch(ForbiddenException e) {
						vista.showMessage(e.getMessage());
					}
				}
			}
		}
	}

	void onEliminar(ActionEvent action) {
		int selectedRows = vista.getTable().getSelectedRowCount();
		if (selectedRows == 1) {
			final int row = vista.getTable().getSelectedRow();
			final int provID = Integer.parseInt(vista.getTableModel().getValueAt(row, 2).toString());
			final String provNombre = vista.getTableModel().getValueAt(row, 0).toString();
			try {
				controller.delete(new ProvinciaDTO(provID, provNombre).getId());
				vaciarTabla();
				llenarTabla();
			} catch (ForbiddenException e) {
				vista.showMessage(e.getMessage());
			}

		}
	}
	
	String [] obtenerNombrePaises() {
		List<PaisDTO> listaPaises = ControllersFactory.getFactory().getPaisController().readAll();
		String [] paises = new String[listaPaises.size()];
		for(int i = 0; i < listaPaises.size(); i++) paises[i] = listaPaises.get(i).getNombre();
		return paises;
	}
}
