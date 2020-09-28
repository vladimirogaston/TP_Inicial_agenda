package presentacion.views;

import java.util.List;

import dto.PaisDTO;

public interface PaisView extends CrudView {

	void clearData();
	
	void setData(List<PaisDTO> dtos);
	
	PaisDTO getData();
	
	void open();
}