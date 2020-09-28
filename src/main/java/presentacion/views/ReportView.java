package presentacion.views;

import java.util.List;

import dto.PersonaDTO;

public interface ReportView {

	void setData(List<PersonaDTO> personas);

	void open();
}
