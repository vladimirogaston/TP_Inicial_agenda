package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ConfigurationService;
import dto.ConfigDatabaseDTO;
import dto.GenericValidator;
import presentacion.views.ConfigurationView;
import presentacion.views.ErrorDialog;
import presentacion.views.WorkbenchView;

public class ConfigurationPresenter {

	private ConfigurationView view;
	private ConfigurationService controller;
	
	public ConfigurationPresenter(ConfigurationView view, ConfigurationService controller){
		this.view = view;
		this.controller = controller;
		onInjectActions();
	}
	
	private void onInjectActions() {
		view.setActionSave((a)->onSave(a));
		view.setActionCancel((a)->onCancel(a));
		view.setActionTest((a)->onTest(a));
	}
	
	private void onTest(ActionEvent a) {
		ConfigDatabaseDTO target = view.getData();
		List<String> errors = GenericValidator.getInstance().validate(target);
		if(errors.isEmpty()) {
			boolean status = controller.test(target);
			if(status) view.setData("Conexion exitosa.");
			else view.setData("Conexion fallida");
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}
	
	private void onSave(ActionEvent a) {	
		ConfigDatabaseDTO target = view.getData();
		List<String> errors = target.validate();
		if(errors.isEmpty()) {
			controller.save(target);
			view.clearData();
			view.close();
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}
	
	private void onCancel(ActionEvent a) {
		view.clearData();
		view.close();
	}
	
	private void onOpen(ActionEvent a) {
		ConfigDatabaseDTO target = controller.getConfiguration();
		if(target != null) {
			view.setData(target);
		}
		view.open();
	}
	
	public ConfigurationPresenter setRootViewAction(WorkbenchView rootView) {
		rootView.setActionConfiguracion(a->onOpen(a));
		return this;
	}
}
