package presentacion;

import java.awt.event.ActionEvent;

import business_logic.ForbiddenException;
import business_logic.PaisController;
import dto.PaisDTO;
import presentacion.views.ErrorDialog;
import presentacion.views.InputDialog;
import presentacion.views.PaisView;
import presentacion.views.WorkbenchView;

public class PaisPresenter {

	private PaisView view = PaisView.getInstance();
	private PaisController controller;

	public PaisPresenter(PaisController controller) {
		assert controller != null;
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemPaises().addActionListener((a)->onInit(a));
	}

	private void onInit(ActionEvent a) {
		reset();
		view.open();
	}
	
	private void onInjectActions() {
		view.setActionSave(a -> onDisplayFormForSave(a));
		view.setActionUpdate(a -> onDisplayFormForUpdate(a));
		view.setActionDelete(s -> onDelete(s));
	}
	
	private void onDisplayFormForSave(ActionEvent a) {
		String input = new InputDialog()
				.title("Ingrese los datos del nuevo país")
				.open();
		if(input != null && !input.trim().isEmpty()) {
			try {
				PaisDTO target = new PaisDTO(input);
				controller.save(target);
				reset();
			}catch(ForbiddenException e) {
				new ErrorDialog().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		PaisDTO current = view.getData();
		if(current != null) {
			String input = new InputDialog()
					.title("Ingrese los nuevos datos del nuevo pais")
					.setText(current.getNombre())
					.open();
			if(input != null && !input.trim().isEmpty()) {
				try {
					PaisDTO target = new PaisDTO(input);
					target.setId(current.getId());
					controller.update(target);
					reset();
				}catch(ForbiddenException e) {
					new ErrorDialog().showMessages(e.getMessage());
				}
			}	
		}
	}
	
	private void onDelete(ActionEvent s) {
		PaisDTO target = view.getData();
		if(target != null) {
			try {
				controller.delete(target.getId());
				reset();
			} catch(ForbiddenException e) {
				new ErrorDialog().showMessages(e.getMessage());
			}
		}
	}
	
	private void reset() {
		view.clearData();
		view.setData(controller.readAll());
	}
}
