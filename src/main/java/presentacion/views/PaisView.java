package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.PaisDTO;

public interface PaisView {

	void clearData();
	
	void setData(List<PaisDTO> dtos);
	
	PaisDTO getData();
	
	void open();

	void setActionSave(ActionListener object);

	void setActionUpdate(ActionListener object);

	void setActionDelete(ActionListener object);
}