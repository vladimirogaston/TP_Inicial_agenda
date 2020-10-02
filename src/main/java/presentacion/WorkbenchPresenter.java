package presentacion;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.Observer;
import business_logic.PersonaController;
import business_logic.local.ConfigurationServiceImpl;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import presentacion.views.ConfigurationView;
import presentacion.views.ErrorDialog;
import presentacion.views.PersonaView;
import presentacion.views.ReporteView;
import presentacion.views.WorkbenchView;

public class WorkbenchPresenter implements Observer, Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();
	private PersonaView formView = PersonaView.getInstance();
	private ConfigurationView configurationView = ConfigurationView.getInstance();

	public WorkbenchPresenter() {
		onInjectWorkbenchActions();
		onInjectFormActions();
	}
	
	@Override
	public void update() {
		workbenchView.clearData();
		workbenchView.setData(getData());
	}	
	
	private void onInjectWorkbenchActions() {
		workbenchView.setActionSave(a -> onDisplayFormForSave(a));
		workbenchView.setActionUpdate(a -> onDisplayFormForUpdate(a));
		workbenchView.setActionDelete(s -> onDelete(s));
		workbenchView.setActionReport(r -> onDisplayReport(r));
	}

	private void onInjectFormActions() {
		formView.setActionSave(p -> onSaveUpdate(p));
		formView.setActionPaisSelect(a -> onUpdateProvinciasList(a));
		formView.setActionProvinciaSelect(a -> onUpdateLocalidadesList(a));
	}

	private void onDelete(ActionEvent s) {
		PersonaDTO target = workbenchView.getData();
		if (target != null) {
			PersonaController controller = ControllersFactory.getFactory().makePersonaController();
			controller.delete(target.getId());
			update();	
		}
	}

	private void onSaveUpdate(ActionEvent p) {
		PersonaDTO target = formView.getData();
		List<String> errors = formView.getData().validate();
		if (errors.isEmpty()) {
			PersonaController controller = ControllersFactory.getFactory().makePersonaController();
			if (target.getId() == null) {
				controller.save(target);
			} else {
				controller.update(target);
			}
			update();
			formView.close();
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}
	
	@Override
	public void onInit() {
		workbenchView.open();
		workbenchView.lockOptions();
		if(!new ConfigurationServiceImpl().isConnectionEnabled()) {
			configurationView.open();
			workbenchView.close();
		}
		workbenchView.unLockOptions();
		update();
	}
	
	private List<PersonaDTO> getData() {
		return ControllersFactory.getFactory().makePersonaController().readAll();
	}

	private void onUpdateProvinciasList(ActionEvent a) {
		String pais = formView.getNombrePaisSeleccionado();
		if (pais != null) {
			List<ProvinciaDTO> provinciaslst = ControllersFactory.getFactory().makeProvinciaController()
					.readByPais(pais);
			ProvinciaDTO[] target = new ProvinciaDTO[provinciaslst.size()];
			formView.setData(provinciaslst.toArray(target));
		}
	}

	private void onUpdateLocalidadesList(ActionEvent a) {
		String provincia = formView.getNombreProvinciaSeleccionada();
		if (provincia != null) {
			List<LocalidadDTO> localidades = ControllersFactory.getFactory().makeLocalidadController()
					.readByProvincia(provincia);
			LocalidadDTO[] target = new LocalidadDTO[localidades.size()];
			formView.setData(localidades.toArray(target));
		}
	}

	private void onDisplayFormForSave(ActionEvent a) {
		formView.clearData();
		fillPaises();
		fillTiposDeContacto();
		formView.open();
	}

	private void onDisplayFormForUpdate(ActionEvent a) {
		PersonaDTO target = workbenchView.getData();
		System.out.println(target);
		if (target != null) {
			formView.clearData();
			fillProvincias(target.getPais());
			fillPaises();
			fillLocalidades(target.getProvincia());			
			fillTiposDeContacto();
			formView.setData(target);
			formView.open();
		}
	}

	private void fillLocalidades(String provincia) {
		if(provincia != null) {
			List<LocalidadDTO> locslst = ControllersFactory.getFactory().makeLocalidadController()
					.readByProvincia(provincia);
			LocalidadDTO[] localidades = new LocalidadDTO[locslst.size()];
			formView.setData(locslst.toArray(localidades));	
		}
	}

	private void fillProvincias(String pais) {
		if(pais != null) {
			List<ProvinciaDTO> provincialst = ControllersFactory.getFactory().makeProvinciaController().readByPais(pais);
			ProvinciaDTO[] provincias = new ProvinciaDTO[provincialst.size()];
			formView.setData(provincialst.toArray(provincias));	
		}
	}

	private void fillPaises() {
		List<PaisDTO> paiseslst = ControllersFactory.getFactory().makePaisController().readAll();
		PaisDTO[] paises = new PaisDTO[paiseslst.size()];
		formView.setData(paiseslst.toArray(paises));
	}

	private void fillTiposDeContacto() {
		List<TipoContactoDTO> tiposlst = ControllersFactory.getFactory().makeTipoController().readAll();
		TipoContactoDTO[] tipos = new TipoContactoDTO[tiposlst.size()];
		formView.setData(tiposlst.toArray(tipos));
	}

	private void onDisplayReport(ActionEvent r) {
		List<PersonaDTO> target = ControllersFactory.getFactory().makePersonaController().readAll();
		Collections.sort(target, Comparator.comparing(PersonaDTO::getNombre));
		ReporteView reporte = new ReporteView();
		reporte.setData(target);
		reporte.open();
	}
}