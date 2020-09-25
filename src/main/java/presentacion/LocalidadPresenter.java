package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ProvinciaController;
import business_logic.ControllersFactoryImpl;
import business_logic.DatabaseException;
import business_logic.LocalidadController;
import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import presentacion.views.LocalidadDialog;
import presentacion.views.LocalidadDriverAdaptor;
import presentacion.views.WorkbenchView;

public class LocalidadPresenter {

	private LocalidadDriverAdaptor adaptor;
	
	public LocalidadPresenter(LocalidadDriverAdaptor vista) {
		adaptor = vista;
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
				.setProvincias(obtenerNombreProvincias())
				.displayForm();
		if(target != null) {
			try {
				LocalidadController controller = ControllersFactoryImpl.getInstance().getLocalidadController();		
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
				.setText(current.getNombre())
				.setProvincias(obtenerNombreProvincias())
				.displayForm();
		if(target != null) {
			try {
				LocalidadController controller = ControllersFactoryImpl.getInstance().getLocalidadController();		
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
				LocalidadController controller = ControllersFactoryImpl.getInstance().getLocalidadController();		
				controller.borrarLocalidad(target);
				reset();
			} catch(DatabaseException e) {
				new ErrorView().showMessages(e.getMessage());
			}
		}
	}
	
	String [] obtenerNombreProvincias() {
		ProvinciaController auxController = ControllersFactoryImpl.getInstance().getProvinciaController();
		List<ProvinciaDTO> lst = auxController.provinciasDisponibles();
		String [] provincias = new String[lst.size()];
		for(int i = 0; i < lst.size(); i++) provincias[i] = lst.get(i).getNombre();
		return provincias;
	}
	
	private void reset() {
		adaptor.clearData();
		LocalidadController controller = ControllersFactoryImpl.getInstance().getLocalidadController();		
		adaptor.setData(controller.localidadesDisponibles());
	}
}
