package presentacion.views;

import java.awt.event.ActionListener;

public interface CrudView {

	void setActionDelete(ActionListener object);

	void setActionUpdate(ActionListener object);

	void setActionSave(ActionListener object);
}
