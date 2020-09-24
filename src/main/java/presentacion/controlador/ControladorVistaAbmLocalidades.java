package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;
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
	
	String [] obtenerNombreProvincias() {
		List<ProvinciaDTO> lst = agenda.provinciasDisponibles();
		String [] provincias = new String[lst.size()];
		for(int i = 0; i < lst.size(); i++) provincias[i] = lst.get(i).getNombre();
		return provincias;
	}
	
	void onEditar(ActionEvent action) {
		if(vista.getTable().getSelectedRowCount() == 1) {
			int row = vista.getTable().getSelectedRow();
			String locNom = vista.getTableModel().getValueAt(row, 0).toString();
			String provNom = vista.getTableModel().getValueAt(row, 1).toString();
			int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 2).toString());
			String [] provincias = obtenerNombreProvincias();
			if(provincias != null) {
				Object [] obj = vista.displayForm(provincias, locNom, provNom);	
				LocalidadDTO dto = new LocalidadDTO(locID, obj[0].toString(), obj[1].toString());
				
				if(dto.getNombre() != null && !dto.getNombre().trim().isEmpty()) {
					try {
						agenda.editarLocalidad(dto);
						vaciarTabla();
						llenarTabla();
					} catch(DatabaseException e) {
						vista.showMessage(e.getMessage());
					}
				}
			}
		}
	}

	void onSalvar(ActionEvent action) {
		String [] provincias = obtenerNombreProvincias();
		Object [] obj = vista.displayForm(provincias);
		String nombre = obj[0].toString();
		String provincia = obj[1] == null ? null : obj[1].toString();
		if(nombre != null && !nombre.trim().isEmpty()) {
			LocalidadDTO dto = new LocalidadDTO(null, nombre, provincia);
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
			final int locID = Integer.parseInt(vista.getTableModel().getValueAt(row, 2).toString());
			final String locNombre = vista.getTableModel().getValueAt(row, 0).toString();
			try {
				agenda.borrarLocalidad(new LocalidadDTO(locID, locNombre));
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
		List<LocalidadDTO> localidades = agenda.localidadesDisponibles();
		for (LocalidadDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getProvincia(), loc.getId() };
			vista.getTableModel().addRow(row);
		}
	}

	public void inicializar(ActionEvent action) {
		vaciarTabla();
		llenarTabla();
		vista.setVisible(true);
	}
}
