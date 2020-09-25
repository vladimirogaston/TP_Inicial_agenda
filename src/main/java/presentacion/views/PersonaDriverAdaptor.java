package presentacion.views;

import java.awt.event.ActionListener;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import presentacion.views.swing.PersonaView;

public class PersonaDriverAdaptor {
	
	PersonaView view = PersonaView.getInstance();
	
	public void clearData() {
		view.getTxtNombre().setText("");
		view.getTxtTelefono().setText("");
		view.getTextFieldEmail().setText("");
		view.getTextField_1Calle().setText("");
		view.getTextFieldPiso().setText("");
		view.getTextFieldAltura().setText("");
		view.getTextFieldDpto().setText("");
		view.getTextFieldEquipo().setText("");
		view.getTextFieldCodigoPostal().setText("");
		view.getComboBoxLocalidad().removeAllItems();
		view.getComboBoxTipoContacto().removeAllItems();
		view.getComboBoxPais().removeAllItems();
		view.setPersonaId(null);
	}
	
	public PersonaDTO getData() {
		Object tipo = view.getComboBoxTipoContacto().getSelectedItem();
		Object pais = view.getComboBoxPais().getSelectedItem();
		Object prov = view.getComboBoxProvincia().getSelectedItem();
		Object loc = view.getComboBoxLocalidad().getSelectedItem();
		return new PersonaDTO
				.Builder(view.getTxtNombre().getText(), view.getTxtTelefono().getText())
				.email(view.getFieldEmail())
				.id(view.getPersonaId() != null ? view.getPersonaId() : 0)
				.fechaNacimiento(view.getFieldFechaDeCumpleaños())
				.tipoContacto(tipo != null ? tipo.toString() : null)
				.calle(view.getTextField_1Calle().getText())
				.altura(view.getTextFieldAltura().getText())
				.piso(view.getTextFieldPiso().getText())
				.dpto(view.getTextFieldPiso().getText())
				.localidad(loc != null ? loc.toString() : null)
				.provincia(prov != null ? prov.toString() : null)
				.codigoPostal(view.getTextFieldCodigoPostal().getText())
				.equipoFutbol(view.getTextFieldEquipo().getText())
				.pais(pais != null ? pais.toString() : null)
				.build();
	}
	
	public void setData(TipoContactoDTO [] tipos) {
		assert tipos != null;
		view.getComboBoxTipoContacto().removeAllItems();
		for (TipoContactoDTO tc : tipos) {
			view.getComboBoxTipoContacto().addItem(tc.getNombre());
		}
	}
	
	public void setData(ProvinciaDTO [] provincias) {
		view.getComboBoxProvincia().removeAllItems();
		for(ProvinciaDTO prov: provincias) {
			view.getComboBoxProvincia().addItem(prov.getNombre());
		}
	}
	
	public void setData(LocalidadDTO [] localidades) {
		view.getComboBoxLocalidad().removeAllItems();
		for (LocalidadDTO loc : localidades) {
			view.getComboBoxLocalidad().addItem(loc.getNombre());
		}
	}
	
	public void setData(PaisDTO [] paises) {
		view.getComboBoxPais().removeAllItems();
		for(PaisDTO pais: paises) {
			view.getComboBoxPais().addItem(pais.getNombre());
		}
	}
	
	public void setData(PersonaDTO persona) {
		view.getTxtNombre().setText(persona.getNombre()); 
		view.getTxtTelefono().setText(persona.getTelefono());
		view.getTextFieldEmail().setText(persona.getEmail());;
		view.setPersonaId(persona.getIdPersona());
		if(persona.getFechaNacimiento() != null)view.getDateChooser().setDate(persona.getFechaNacimiento());
		view.getComboBoxTipoContacto().setSelectedItem(persona.getTipoContacto());
		view.getTextField_1Calle().setText(persona.getCalle());
		view.getTextFieldAltura().setText(persona.getAltura());
		view.getTextFieldPiso().setText(persona.getPiso());
		view.getTextFieldDpto().setText(persona.getDpto());
		view.getComboBoxLocalidad().setSelectedItem(persona.getLocalidad());
		view.getComboBoxProvincia().setSelectedItem(persona.getProvincia());
		view.getTextFieldCodigoPostal().setText(persona.getCodigoPostal());
		view.getTextFieldEquipo().setText(persona.getEquipoFutbol());
		view.getComboBoxPais().setSelectedItem(persona.getPais());
	}
		
	public String getNombreProvinciaSeleccionada() {
		Object obj = view.getComboBoxProvincia().getSelectedItem();
		return obj != null ? obj.toString() : "";
	}
	
	public String getNombrePaisSeleccionado() {
		Object obj = view.getComboBoxPais().getSelectedItem();
		return obj != null ? obj.toString() : "";
	}
	
	public void setActionSave(ActionListener listener) {
		assert listener != null;
		view.getBtnAgregarPersona().addActionListener(listener);
	}
	
	public void setActionProvinciaSelect(ActionListener listener) {
		assert listener != null;
		view.getComboBoxProvincia().addActionListener(listener);
	}
	
	public void setActionPaisSelect(ActionListener listener) {
		assert listener != null;
		view.getComboBoxPais().addActionListener(listener);
	}
	
	public void open() {
		view.setVisible(true);
	}
	
	public void close() {
		view.setVisible(false);
	}
}
