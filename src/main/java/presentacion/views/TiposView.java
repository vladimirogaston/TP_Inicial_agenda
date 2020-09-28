package presentacion.views;

import java.awt.event.ActionListener;
import java.util.List;

import dto.TipoContactoDTO;

public interface TiposView {

	void setActionDelete(ActionListener object);

	void setActionUpdate(ActionListener object);

	void setActionSave(ActionListener object);

	void open();

	TipoContactoDTO getData();

	void setData(List<TipoContactoDTO> dtos);

	void clearData();
}
