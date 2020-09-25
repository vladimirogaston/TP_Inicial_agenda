package presentacion;

import java.awt.event.ActionEvent;
import business_logic.ControllersFactoryImpl;
import business_logic.ConflictException;
import business_logic.PaisController;
import dto.PaisDTO;
import presentacion.views.PaisDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.InputDialog;
import presentacion.views.swing.WorkbenchView;

public class PaisPresenter {

	private PaisDriverAdaptor adaptor;
	private PaisController controller;

	public PaisPresenter(PaisDriverAdaptor vista) {
		adaptor = vista;
		controller = ControllersFactoryImpl.getInstance().getPaisController();
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemPaises().addActionListener((a)->onInit(a));
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
				.title("Ingrese los datos del nuevo país")
				.displayForm();
		if(input != null) {
			try {
				PaisDTO target = new PaisDTO(input);
				controller.agregarPais(target);
				reset();
			}catch(ConflictException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		PaisDTO current = adaptor.getData();
		String input = new InputDialog()
				.title("Ingrese los nuevos datos del nuevo pais")
				.setText(current.getNombre())
				.displayForm();
		if(input != null) {
			try {
				PaisDTO target = new PaisDTO(input);
				target.setId(current.getId());
				controller.editarPais(target);
				reset();
			}catch(ConflictException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		PaisDTO target = adaptor.getData();
		if(target != null) {
			try {
				controller.borrarPais(target);
				reset();
			} catch(ConflictException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.paisesDisponibles());
	}
}
