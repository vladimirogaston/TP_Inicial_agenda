package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.exceptions.ForbiddenException;
import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import presentacion.views.LocalidadView;
import presentacion.views.WorkbenchViewImpl;
import presentacion.views.swing.ErrorDialogImpl;
import presentacion.views.swing.InputSelectDialogImpl;

public class LocalidadPresenter {
	
	private LocalidadView adaptor;
	
	private LocalidadController controller;
	
	public LocalidadPresenter(LocalidadView vista, LocalidadController controller) {
		assert vista != null;
		assert controller != null;
		this.adaptor = vista;
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchViewImpl.getInstance().getMntmNewMenuItemLocalidades().addActionListener((a)->onInit(a));
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
		String [] target = new InputSelectDialogImpl()
				.title("Ingrese los datos de la nueva localidad")
				.setProvincias(getNombreProvincias())
				.open();
		LocalidadDTO dto = new LocalidadDTO(null, target[0], target[1]);
		if(dto.getNombre() != null) {
			try {
				controller.save(dto);
				reset();
			}catch(ForbiddenException e) {
				new ErrorDialogImpl().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		LocalidadDTO current = adaptor.getData();
		if(current != null) {
			String [] target = new InputSelectDialogImpl()
					.title("Ingrese los datos de la nueva localidad")
					.setProvincias(getNombreProvincias())
					.open();
			LocalidadDTO dto = new LocalidadDTO(null, target[0], target[1]);
			if(dto.getNombre() != null) {
				try {
					dto.setId(current.getId());
					controller.update(dto);
					reset();
				}catch(ForbiddenException e) {
					new ErrorDialogImpl().showMessages(e.getMessage());
				}
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		LocalidadDTO target = adaptor.getData();
		if(target != null) {
			try {
				int id = target.getId();
				controller.delete(id);
				reset();
			} catch(ForbiddenException e) {
				new ErrorDialogImpl().showMessages(e.getMessage());
			}
		}
	}
	
	private String [] getNombreProvincias() {
		List<String> lst = new LinkedList<>();
		for(ProvinciaDTO aux : ControllersFactory.getFactory().getProvinciaController().readAll()) {
			lst.add(aux.getNombre());
		}
		String [] provincias = new String[lst.size()];
		return lst.toArray(provincias);
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.readAll());
	}
}