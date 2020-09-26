package presentacion;

import java.awt.event.ActionEvent;

import business_logic.PaisController;
import business_logic.exceptions.ForbiddenException;
import dto.PaisDTO;
import presentacion.views.PaisDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.InputDialog;
import presentacion.views.swing.WorkbenchView;

public class PaisPresenter {

	private PaisDriverAdaptor view;
	private PaisController controller;

	public PaisPresenter(PaisDriverAdaptor vista, PaisController controller) {
		assert vista != null;
		assert controller != null;
		this.view = vista;
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
				.displayForm();
		if(input != null) {
			try {
				PaisDTO target = new PaisDTO(input);
				controller.save(target);
				reset();
			}catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		PaisDTO current = view.getData();
		String input = new InputDialog()
				.title("Ingrese los nuevos datos del nuevo pais")
				.setText(current.getNombre())
				.displayForm();
		if(input != null) {
			try {
				PaisDTO target = new PaisDTO(input);
				target.setId(current.getId());
				controller.update(target);
				reset();
			}catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		PaisDTO target = view.getData();
		if(target != null) {
			try {
				controller.delete(target);
				reset();
			} catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void reset() {
		view.clearData();
		view.setData(controller.readAll());
	}
}
