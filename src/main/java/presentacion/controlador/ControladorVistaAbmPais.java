package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.PaisDTO;
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
	}

	void inicializar(ActionEvent action){
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
		for(PaisDTO pais : paises) {
			Object[] row = {pais.getNombre(), pais.getId() };
			vista.getTableModel().addRow(row);
		}
	}

	public VistaAbmPais getVista() {
		return vista;
	}

	public Agenda getAgenda() {
		return agenda;
	}
}
