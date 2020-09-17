package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.VistaAbmLocalidades;
import dto.GenericValidator;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {

	Vista vista;
	VentanaPersona ventanaPersona;
	List<PersonaDTO> personasEnTabla;
	Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(a -> ventanaEditarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		this.agenda = agenda;
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		this.refrescarTabla();
	}

	void fillComboboxes() {
		for (LocalidadDTO loc : agenda.localidadesDisponibles())
			ventanaPersona.fillLocalidades(loc.getNombre());
		for (TipoContactoDTO tc : agenda.tiposDisponibles())
			ventanaPersona.fillTiposContacto(tc.getNombre());
	}

	void ventanaAgregarPersona(ActionEvent a) {
		fillComboboxes();
		this.ventanaPersona.mostrarVentana();
	}

	void ventanaEditarPersona(ActionEvent a) {
		fillComboboxes();
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		if (filasSeleccionadas.length == 1) {
			try {
				fillViewWithDto();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ventanaPersona.mostrarVentana();
		}
	}

	void fillViewWithDto() throws ParseException {
		int row = vista.getTablaPersonas().getSelectedRow();
		ventanaPersona.getTxtNombre().setText(getValueAt(row, 0));
		ventanaPersona.getTxtTelefono().setText(getValueAt(row, 1));
		ventanaPersona.getTextFieldEmail().setText(getValueAt(row, 2));
		ventanaPersona.getDateChooser().setDate(new SimpleDateFormat("yyyy-MM-dd").parse(getValueAt(row, 3)));
		ventanaPersona.getComboBoxTipoContacto().setSelectedItem((Object) (getValueAt(row, 4)));
		ventanaPersona.getTextField_1Calle().setText(getValueAt(row, 5));
		ventanaPersona.getTextFieldAltura().setText(getValueAt(row, 6));
		ventanaPersona.getTextFieldPiso().setText(getValueAt(row, 7));
		ventanaPersona.getTextFieldDpto().setText(getValueAt(row, 8));
		ventanaPersona.getComboBoxLocalidad().setSelectedItem(getValueAt(row, 9));
		ventanaPersona.setPersonaId(Integer.parseInt(getValueAt(row, 10)));
	}

	String getValueAt(int row, int column) {
		return vista.getModelPersonas().getValueAt(row, column).toString();
	}

	void guardarPersona(ActionEvent p) {
		PersonaDTO nuevaPersona = getFromView();
		String errors = GenericValidator.getInstance().validate(nuevaPersona);
		if (errors.isEmpty()) {
			if (ventanaPersona.getPersonaId() == null) {
				agenda.agregarPersona(nuevaPersona);
			} else {
				agenda.editar(nuevaPersona);
			}
			refrescarTabla();
			ventanaPersona.cerrar();
		} else {
			vista.showMessages(errors);
		}
	}

	PersonaDTO getFromView() {
		VentanaPersona view = ventanaPersona;
		return new PersonaDTO.Builder(view.getFieldNombre(), view.getFieldTelefono()).email(view.getFieldEmail())
				.id(ventanaPersona.getPersonaId() != null ? ventanaPersona.getPersonaId() : 0)
				.fechaNacimiento(view.getFieldFechaDeCumpleaños()).tipoContacto(view.getFieldTipoDeContacto())
				.calle(view.getFieldCalle()).altura(view.getFieldAltura()).piso(view.gettFieldPiso())
				.dpto(view.getFieldDepartamento()).localidad(view.getFieldLocalidad()).build();
	}

	void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}
}
