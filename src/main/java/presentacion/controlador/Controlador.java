package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class Controlador {

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
		ventanaPersona.getComboBoxProvincia().addActionListener(a->actualizarListaLocalidades(a));
		this.agenda = agenda;
	}

	void actualizarListaLocalidades(ActionEvent a) {
		Object obj = ventanaPersona.getComboBoxProvincia().getSelectedItem();
		if(obj != null) {
			String provinciaSeleccionada = obj.toString();
			if(provinciaSeleccionada != null) {}
			ventanaPersona.getComboBoxLocalidad().removeAllItems();
			for(LocalidadDTO localidad : agenda.localidadPorProvincia(provinciaSeleccionada)) {
				ventanaPersona.fillLocalidades(localidad.getNombre());
			}	
		}
	}
	
	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		this.refrescarTabla();
	}

	void fillComboboxes() {
		ventanaPersona.getComboBoxTipoContacto().removeAllItems();
		for (TipoContactoDTO tc : agenda.tiposDisponibles()) {
			ventanaPersona.fillTiposContacto(tc.getNombre());
		}
		ventanaPersona.getComboBoxProvincia().removeAllItems();
		for(ProvinciaDTO prov: agenda.provinciasDisponibles()) {
			ventanaPersona.fillProvincias(prov.getNombre());
		}
		Object item = ventanaPersona.getComboBoxProvincia().getSelectedItem();
		if(item != null) {
			String provincia = ventanaPersona.getComboBoxProvincia().getSelectedItem().toString();
			ventanaPersona.getComboBoxLocalidad().removeAllItems();
			for (LocalidadDTO loc : agenda.localidadPorProvincia(provincia)) {
				ventanaPersona.fillLocalidades(loc.getNombre());
			}
		}
		
		ventanaPersona.getComboBoxPais().removeAllItems();
		for(PaisDTO pais: agenda.paisesDisponibles()) {
			ventanaPersona.fillPaises(pais.getNombre());
		}
	}

	void ventanaAgregarPersona(ActionEvent a) {
		ventanaPersona.limpiar();
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
		if(!getValueAt(row, 3).isEmpty())ventanaPersona.getDateChooser().setDate(new SimpleDateFormat("yyyy-MM-dd").parse(getValueAt(row, 3)));
		ventanaPersona.getComboBoxTipoContacto().setSelectedItem((Object) (getValueAt(row, 4)));
		ventanaPersona.getTextField_1Calle().setText(getValueAt(row, 5));
		ventanaPersona.getTextFieldAltura().setText(getValueAt(row, 6));
		ventanaPersona.getTextFieldPiso().setText(getValueAt(row, 7));
		ventanaPersona.getTextFieldDpto().setText(getValueAt(row, 8));
		ventanaPersona.getComboBoxLocalidad().setSelectedItem(getValueAt(row, 9));
		ventanaPersona.getComboBoxProvincia().setSelectedItem(getValueAt(row, 10));
		ventanaPersona.getComboBoxPais().setSelectedItem(getValueAt(row, 11));
		ventanaPersona.getTextFieldEquipo().setText(getValueAt(row, 12));
		ventanaPersona.getTextFieldCodigoPostal().setText(getValueAt(row, 13));
		ventanaPersona.setPersonaId(Integer.parseInt(getValueAt(row, 14)));
	}

	String getValueAt(int row, int column) {
		Object obj = vista.getModelPersonas().getValueAt(row, column);
		if(obj != null) return vista.getModelPersonas().getValueAt(row, column).toString();
		else return "";
	}

	void guardarPersona(ActionEvent p) {
		PersonaDTO nuevaPersona = getFromView();
		List<String> errors = nuevaPersona.validate();
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
		return new PersonaDTO
				.Builder(view.getFieldNombre(), view.getFieldTelefono())
				.email(view.getFieldEmail())
				.id(ventanaPersona.getPersonaId() != null ? ventanaPersona.getPersonaId() : 0)
				.fechaNacimiento(view.getFieldFechaDeCumpleaños())
				.tipoContacto(view.getFieldTipoDeContacto())
				.calle(view.getFieldCalle())
				.altura(view.getFieldAltura())
				.piso(view.gettFieldPiso())
				.dpto(view.getFieldDepartamento())
				.localidad(view.getFieldLocalidad())
				.provincia(view.getProvincia())
				.codigoPostal(view.getTextFieldCodigoPostal().getText())
				.equipoFutbol(view.getTextFieldEquipo().getText())
				.pais(view.getPais())
				.build();
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
