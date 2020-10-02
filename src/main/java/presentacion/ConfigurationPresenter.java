package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ConfigurationService;
import dto.ConfigDatabaseDTO;
import presentacion.views.ConfigurationView;
import presentacion.views.ErrorDialog;

public class ConfigurationPresenter {

	private ConfigurationView view = ConfigurationView.getInstance();
	private ConfigurationService controller;
	
	public ConfigurationPresenter(ConfigurationService controller) {
		this.controller = controller;
		onInjectActions();
	}
	
	private void onInjectActions() {
		view.setActionSave((a)->onSave(a));
		view.setActionLocalhost(a->onSelectLocalhost(a));
	}
	
	private void onSelectLocalhost(ActionEvent a) {
		if(view.getData().isLocalhost()) {
			view.clearIp();
			view.disableIp();
		} else {
			view.enableIp();
		}
	}
		
	private void onSave(ActionEvent a) {	
		ConfigDatabaseDTO target = view.getData();
		List<String> errors = target.validate();
		if(errors.isEmpty()) {
			if(controller.onConnect(target)) {
				controller.save(target);
				view.clearData();
				view.close();
			}
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}
}
