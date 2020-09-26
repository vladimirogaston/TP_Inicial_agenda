package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.LocalidadController;
import business_logic.exceptions.ForbiddenException;
import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import presentacion.views.LocalidadDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.LocalidadDialog;
import presentacion.views.swing.WorkbenchView;

public class LocalidadPresenter {

	private LocalidadDriverAdaptor adaptor;
	private LocalidadController controller;
	
	public LocalidadPresenter(LocalidadDriverAdaptor vista, LocalidadController controller) {
		assert vista != null;
		assert controller != null;
		this.adaptor = vista;
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance().getMntmNewMenuItemLocalidades().addActionListener((a)->onInit(a));
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
		LocalidadDTO target = new LocalidadDialog()
				.title("Ingrese los datos de la nueva localidad")
				.setProvincias(obtenerProvincias())
				.displayForm();
		if(target != null) {
			try {
				controller.save(target);
				reset();
			}catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		LocalidadDTO current = adaptor.getData();
		if(current != null) {
			LocalidadDTO target = new LocalidadDialog()
					.title("Ingrese los nuevos datos de la localidad")
					.setProvincias(obtenerProvincias())
					.setText(current.getNombre())
					.setNombreProvincia(current.getProvincia())
					.displayForm();
			if(target != null) {
				try {
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
		LocalidadDTO target = adaptor.getData();
		if(target != null) {
			try {
				int id = target.getId();
				controller.delete(id);
				reset();
			} catch(ForbiddenException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private ProvinciaDTO [] obtenerProvincias() {
		List<ProvinciaDTO> lst = ControllersFactory.getFactory().getProvinciaController().readAll();
		ProvinciaDTO [] provincias = new ProvinciaDTO[lst.size()];
		return lst.toArray(provincias);
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.readAll());
	}
}
