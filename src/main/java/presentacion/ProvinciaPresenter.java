package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.ForbiddenException;
import business_logic.ProvinciaController;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import presentacion.views.ErrorDialog;
import presentacion.views.InputSelectDialog;
import presentacion.views.ProvinciaView;
import presentacion.views.WorkbenchView;
import repositories.jdbc.ConstraintViolationException;

public class ProvinciaPresenter {
	
	private ProvinciaView view = ProvinciaView.getInstance();
	
	private ProvinciaController controller;
	
	public ProvinciaPresenter(ProvinciaController controller) {
		assert controller != null;
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemProvincias().addActionListener((a)->onInit(a));
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
		String [] target = new InputSelectDialog()
				.title("Ingrese los datos de la nueva provincia")
				.setData(getNombrePaises())
				.open();
		if(target == null) return;
		ProvinciaDTO dto = new ProvinciaDTO(null, target[0], target[1]);
		if(dto.getNombre() != null) {
			try {
				if(dto.getPais() == null) {
					dto.setPais("");
				}
				controller.save(dto);
				reset();
			} catch (ForbiddenException e) {
				new ErrorDialog().showMessages(e.getMessage());
			}
		} 
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		ProvinciaDTO current = view.getData();
		if(current != null) {
			String [] target = new InputSelectDialog()
					.title("Ingrese los datos de la nueva Provincia")
					.setText(current.getNombre())
					.setData(getNombrePaises())
					.open();
			if(target == null) return;
			ProvinciaDTO dto = new ProvinciaDTO(null, target[0], target[1]);
			if(dto.getNombre() != null) {
				try {
					dto.setId(current.getId());
					controller.update(dto);
					reset();
				}catch(ForbiddenException e) {
					new ErrorDialog().showMessages(e.getMessage());
				}
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		ProvinciaDTO target = view.getData();
		if(target != null) {
			try {
				int id = target.getId();
				controller.deleteById(id);
				reset();
			} catch(ConstraintViolationException e) {
				new ErrorDialog().showMessages("No se puede eliminar una provincia en uso.");
			}
		}
	}
	
	private String [] getNombrePaises() {
		List<String> lst = new LinkedList<>();
		for(PaisDTO aux : ControllersFactory.getFactory().makePaisController().readAll()) {
			lst.add(aux.getNombre());
		}
		String [] paises = new String[lst.size()];
		return lst.toArray(paises);
	}
	
	private void reset() {
		view.clearData();
		view.setData(controller.readAll());
	}
}
