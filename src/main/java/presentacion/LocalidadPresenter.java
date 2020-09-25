package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactoryImpl;
import business_logic.LocalidadController;
import business_logic.DatabaseException;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import presentacion.views.LocalidadDriverAdaptor;
import presentacion.views.swing.ErrorView;
import presentacion.views.swing.LocalidadDialog;
import presentacion.views.swing.WorkbenchView;

public class LocalidadPresenter {

	private LocalidadDriverAdaptor adaptor;
	private LocalidadController controller;
	
	public LocalidadPresenter(LocalidadDriverAdaptor vista) {
		adaptor = vista;
		controller = ControllersFactoryImpl.getInstance().getLocalidadController();
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
				.setProvincias(obtenerNombreProvincias())
				.displayForm();
		if(target != null) {
			try {
				controller.agregarLocalidad(target);
				reset();
			}catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		LocalidadDTO current = adaptor.getData();
		LocalidadDTO target = new LocalidadDialog()
				.title("Ingrese los nuevos datos de la localidad")
				.setText(current.getNombre())
				.setProvincias(obtenerNombreProvincias())
				.displayForm();
		if(target != null) {
			try {
				target.setId(current.getId());
				controller.editarLocalidad(target);
				reset();
			}catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDelete(ActionEvent s) {
		LocalidadDTO target = adaptor.getData();
		if(target != null) {
			try {
				controller.borrarLocalidad(target);
				reset();
			} catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	private String [] obtenerNombreProvincias() {
		List<ProvinciaDTO> lst = ControllersFactoryImpl.getInstance().getProvinciaController().provinciasDisponibles();
		String [] provincias = new String[lst.size()];
		return lst.toArray(provincias);
	}
	
	private void reset() {
		adaptor.clearData();
		adaptor.setData(controller.localidadesDisponibles());
	}
}
