package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

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

	String [] obtenerNombrePaises() {
		List<PaisDTO> listaPaises = agenda.paisesDisponibles();
		String [] paises = new String[listaPaises.size()];
		for(int i = 0; i < listaPaises.size(); i++) paises[i] = listaPaises.get(i).getNombre();
		return paises;
	}

}
