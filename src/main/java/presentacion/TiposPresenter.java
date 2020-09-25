package presentacion;

import java.awt.event.ActionEvent;
import business_logic.TipoController;
import business_logic.ControllersFactoryImpl;
import business_logic.DatabaseException;
import dto.TipoContactoDTO;
import presentacion.views.TiposDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.InputDialog;
import presentacion.views.swing.WorkbenchView;

public class TiposPresenter {

	private TiposDriverAdaptor adaptor;
	private TipoController controller;

	public TiposPresenter(TiposDriverAdaptor adaptor) {
		this.adaptor = adaptor;
		controller = ControllersFactoryImpl.getInstance().getTipoController();
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemTipos().addActionListener((a)->onInit(a));
	}

	private void onInit(ActionEvent a) {
		reset();
		adaptor.open();
	}
	
	private void onInjectActions() {
		adaptor.setActionSave(a -> onDisplayFormForSave(a));
		adaptor.setActionUpdate(a -> onDisplayFormForUpdate(a));
		adaptor.setActionDelete(s -> onDelete(s));
	}
	
	private void onDisplayFormForSave(ActionEvent a) {
		String input = new InputDialog()
				.title("Ingrese el nombre del nuevo tipo de contacto")
				.displayForm();
		if(input != null) {
			try {
				TipoContactoDTO target = new TipoContactoDTO(input);
				controller.agregarTipoDeContacto(target);
				reset();
			}catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		TipoContactoDTO current = adaptor.getData();
		String input = new InputDialog()
				.title("Ingrese el nuevo nombre del tipo de contacto")
				.setText(current.getNombre())
				.displayForm();
		if(input != null) {
			try {
				TipoContactoDTO target = new TipoContactoDTO(input);
				target.setId(current.getId());
				controller.editarTipoDeContacto(target);
				reset();
			}catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		TipoContactoDTO target = adaptor.getData();
		if(target != null) {
			try {
				controller.borrarTipoDeContacto(target);
				reset();
			} catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.tiposDisponibles());
	}
}
