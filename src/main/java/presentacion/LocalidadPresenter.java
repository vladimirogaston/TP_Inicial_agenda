package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.ForbiddenException;
import business_logic.LocalidadController;
import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import presentacion.views.ErrorDialog;
import presentacion.views.InputSelectDialog;
import presentacion.views.LocalidadView;
import presentacion.views.WorkbenchView;
import repositories.jdbc.ConstraintViolationException;

public class LocalidadPresenter {
	
	private LocalidadView view = LocalidadView.getInstance();
	
	private LocalidadController controller;
	
	public LocalidadPresenter(LocalidadController controller) {
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemLocalidades().addActionListener((a)->onInit(a));
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
				.title("Ingrese los datos de la nueva localidad")
				.setData(getNombreProvincias())
				.open();
		if(target == null) return;
		LocalidadDTO dto = new LocalidadDTO(null, target[0], target[1]);
		if(dto.getNombre() != null) {
			try {
				if(dto.getProvincia() == null) {
					dto.setProvincia("");
				}
				controller.save(dto);
				reset();
			} catch (ForbiddenException e) {
				new ErrorDialog().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		LocalidadDTO current = view.getData();
		if(current != null) {
			String [] target = new InputSelectDialog()
					.title("Ingrese los datos de la nueva localidad")
					.setText(current.getNombre())
					.setData(getNombreProvincias())
					.open();
			if(target == null) return;
			LocalidadDTO dto = new LocalidadDTO(null, target[0], target[1]);
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
		LocalidadDTO target = view.getData();
		if(target != null) {
			try {
				int id = target.getId();
				controller.deleteById(id);
				reset();
			} catch(ConstraintViolationException e) {
				new ErrorDialog().showMessages("No se puede eliminar una localidad en uso");
			}
		}
	}
	
	private String [] getNombreProvincias() {
		List<String> lst = new LinkedList<>();
		for(ProvinciaDTO aux : ControllersFactory.getFactory().makeProvinciaController().readAll()) {
			lst.add(aux.getNombre());
		}
		String [] provincias = new String[lst.size()];
		return lst.toArray(provincias);
	}
	
	private void reset() {
		view.clearData();
		view.setData(controller.readAll());
	}
}