package presentacion.views;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadView extends CrudView {

	void clearData();

	void setData(List<LocalidadDTO> localidades);

	LocalidadDTO getData();

	void open();
}
