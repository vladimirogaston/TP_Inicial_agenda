package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadView {

	void clearData();

	void setData(List<LocalidadDTO> localidades);

	LocalidadDTO getData();

	void open();

	void setActionSave(ActionListener object);

	void setActionUpdate(ActionListener object);

	void setActionDelete(ActionListener object);
}
