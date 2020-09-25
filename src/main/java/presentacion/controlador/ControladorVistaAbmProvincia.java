package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Agenda;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmProvincia;

public class ControladorVistaAbmProvincia {

	private VistaAbmProvincia vista;
	private Agenda agenda;

	public ControladorVistaAbmProvincia(VistaAbmProvincia vista, Agenda agenda) {
		super();
		this.vista = vista;
		this.agenda = agenda;
		vista.getTable().getColumn("ID").setPreferredWidth(0);
		Vista.getInstance().getMntmNewMenuItemProvincias().addActionListener((a) -> inicializar(a));
		vista.getBtnSalvar().addActionListener((a) -> onSalvar(a));
		vista.getBtnEditar().addActionListener((a) -> onEditar(a));
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
		List<ProvinciaDTO> provincias = agenda.provinciasDisponibles();
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
				agenda.agregarProvincia(provinciaDTO);
				vaciarTabla();
				llenarTabla();
			} catch(DatabaseException e) {
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
						agenda.editarProvincia(provinciaDTO);
						vaciarTabla();
						llenarTabla();
					} catch(DatabaseException e) {
						vista.showMessage(e.getMessage());
					}
				}
			}
		}
	}

	String [] obtenerNombrePaises() {
		List<PaisDTO> listaPaises = agenda.paisesDisponibles();
		String [] paises = new String[listaPaises.size()];
		for(int i = 0; i < listaPaises.size(); i++) paises[i] = listaPaises.get(i).getNombre();
		return paises;
	}

}
