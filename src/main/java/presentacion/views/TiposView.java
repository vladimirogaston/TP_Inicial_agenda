package presentacion.views;

import java.util.List;

import dto.TipoContactoDTO;

public interface TiposView extends CrudView {

	void open();

	TipoContactoDTO getData();

	void setData(List<TipoContactoDTO> dtos);

	void clearData();
}
