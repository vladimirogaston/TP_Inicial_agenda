package presentacion.views;

import java.awt.event.ActionListener;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public interface PersonaView {

	void clearData();
	
	PersonaDTO getData();
	
	void setData(TipoContactoDTO [] tipos);
	
	void setData(ProvinciaDTO [] provincias);
	
	void setData(LocalidadDTO [] localidades);
	
	void setData(PaisDTO [] paises);
	
	void setData(PersonaDTO persona);
	
	void open();
	
	void close();
	
	String getNombreProvinciaSeleccionada();
	
	String getNombrePaisSeleccionado();
	
	void setActionSave(ActionListener listener);
	
	void setActionProvinciaSelect(ActionListener listener);
	
	void setActionPaisSelect(ActionListener listener);
}
