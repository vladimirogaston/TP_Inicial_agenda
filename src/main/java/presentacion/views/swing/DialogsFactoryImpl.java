package presentacion.views.swing;

public class DialogsFactoryImpl {

	public ErrorDialogImpl makeErrorDialog() {
		return new ErrorDialogImpl();
	}

	public InputDialogImpl makeInputDialog() {
		return new InputDialogImpl();
	}

	public InputSelectDialogImpl makeInputSelectDialog() {
		return new InputSelectDialogImpl();
	}
}
