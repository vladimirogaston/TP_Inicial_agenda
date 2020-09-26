package presentacion;

import java.awt.event.ActionEvent;

import business_logic.TipoController;
import business_logic.exceptions.ForbiddenException;
import dto.TipoContactoDTO;
import presentacion.views.TiposDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.InputDialog;
import presentacion.views.swing.WorkbenchView;

public class TiposPresenter {

	private TiposDriverAdaptor adaptor;
	private TipoController controller;

	public TiposPresenter(TiposDriverAdaptor adaptor, TipoController controller) {
		this.adaptor = adaptor;
		this.controller = controller;
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
		adaptor.setActionSave(a -> onSave(a));
		adaptor.setActionUpdate(a -> onUpdate(a));
		adaptor.setActionDelete(s -> onDelete(s));
	}
	
	private void onSave(ActionEvent a) {
		String input = new InputDialog()
				.title("Ingrese el nombre del nuevo tipo de contacto")
				.displayForm();
		if(input != null) {
			try {
				TipoContactoDTO target = new TipoContactoDTO(input);
				controller.save(target);
				reset();
			}catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onUpdate(ActionEvent a) {
		TipoContactoDTO current = adaptor.getData();
		if(current != null) {
			String input = new InputDialog()
					.title("Ingrese el tipo de contacto")
					.setText(current.getNombre())
					.displayForm();
			if(input != null) {
				try {
					TipoContactoDTO target = new TipoContactoDTO(input);
					target.setId(current.getId());
					controller.update(target);
					reset();
				}catch(ForbiddenException e) {
					new ErrorView().showMessages(e.getMessage());
				}
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		TipoContactoDTO target = adaptor.getData();
		if(target != null) {
			try {
				controller.delete(target.getId());
				reset();
			} catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.readAll());
	}
}
