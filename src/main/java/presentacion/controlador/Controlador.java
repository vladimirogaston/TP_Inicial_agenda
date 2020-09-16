package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
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
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		this.agenda = agenda;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)	this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		this.refrescarTabla();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	void ventanaAgregarPersona(ActionEvent a) {
		for (LocalidadDTO loc : agenda.localidadesDisponibles())ventanaPersona.fillLocalidades(loc.getNombre());
		for (TipoContactoDTO tc : agenda.tiposDisponibles())ventanaPersona.fillTiposContacto(tc.getNombre());
		this.ventanaPersona.mostrarVentana();
	}

	void guardarPersona(ActionEvent p) {
		PersonaDTO nuevaPersona = getFromView();
		String errors = GenericValidator.getInstance().validate(nuevaPersona);
		if (errors.isEmpty()) {
			agenda.agregarPersona(nuevaPersona);
			refrescarTabla();
			ventanaPersona.cerrar();
		} else {
			vista.showMessages(errors);
		}
	}

	PersonaDTO getFromView() {
		VentanaPersona view = ventanaPersona;
		return new PersonaDTO.Builder(view.getFieldNombre(), view.getFieldTelefono()).email(view.getFieldEmail())
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
