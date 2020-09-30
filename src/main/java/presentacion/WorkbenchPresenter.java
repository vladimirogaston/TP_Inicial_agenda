package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.Observer;
import business_logic.PersonaController;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import presentacion.views.WorkbenchViewImpl;
import presentacion.views.swing.ErrorDialogImpl;
import presentacion.views.swing.PersonaViewImpl;
import presentacion.views.swing.ReporteViewImpl;

public class WorkbenchPresenter implements Observer {

	private WorkbenchViewImpl workbenchView;
	private PersonaViewImpl formView;

	public WorkbenchPresenter(WorkbenchViewImpl view, PersonaViewImpl formView) {
		assert view != null;
		assert formView != null;
		workbenchView = view;
		this.formView = formView;
		onInjectWorkbenchActions();
		onInjectFormActions();
	}
	
	@Override
	public void update() {
		workbenchView.clearData();
		PersonaDTO [] personas = getData();
		if(personas != null) workbenchView.setData(personas);
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
			PersonaController controller = ControllersFactory.getFactory().getPersonaController();
			controller.delete(target.getId());
			update();	
		}
	}

	private void onSaveUpdate(ActionEvent p) {
		PersonaDTO target = formView.getData();
		List<String> errors = formView.getData().validate();
		if (errors.isEmpty()) {
			PersonaController controller = ControllersFactory.getFactory().getPersonaController();
			if (target.getId() == null) {
				controller.save(target);
			} else {
				controller.update(target);
			}
			update();
			formView.close();
		} else {
			new ErrorDialogImpl().showMessages(errors);
		}
	}
	
	public void onInit() {
		update();
		workbenchView.open();
	}
	
	private PersonaDTO [] getData() {
		List<PersonaDTO> personas = ControllersFactory.getFactory().getPersonaController().readAll();
		return personas.toArray(new PersonaDTO[personas.size()]);
	}

	private void onUpdateProvinciasList(ActionEvent a) {
		String pais = formView.getNombrePaisSeleccionado();
		if (pais != null) {
			List<ProvinciaDTO> provinciaslst = ControllersFactory.getFactory().getProvinciaController()
					.readByPais(pais);
			ProvinciaDTO[] target = new ProvinciaDTO[provinciaslst.size()];
			formView.setData(provinciaslst.toArray(target));
		}
	}

	private void onUpdateLocalidadesList(ActionEvent a) {
		String provincia = formView.getNombreProvinciaSeleccionada();
		if (provincia != null) {
			List<LocalidadDTO> localidades = ControllersFactory.getFactory().getLocalidadController()
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
		List<LocalidadDTO> locslst = ControllersFactory.getFactory().getLocalidadController()
				.readByProvincia(provincia);
		LocalidadDTO[] localidades = new LocalidadDTO[locslst.size()];
		formView.setData(locslst.toArray(localidades));
	}

	private void fillProvincias(String pais) {
		List<ProvinciaDTO> provincialst = ControllersFactory.getFactory().getProvinciaController().readByPais(pais);
		ProvinciaDTO[] provincias = new ProvinciaDTO[provincialst.size()];
		formView.setData(provincialst.toArray(provincias));
	}

	private void fillPaises() {
		List<PaisDTO> paiseslst = ControllersFactory.getFactory().getPaisController().readAll();
		PaisDTO[] paises = new PaisDTO[paiseslst.size()];
		formView.setData(paiseslst.toArray(paises));
	}

	private void fillTiposDeContacto() {
		List<TipoContactoDTO> tiposlst = ControllersFactory.getFactory().getTipoController().readAll();
		TipoContactoDTO[] tipos = new TipoContactoDTO[tiposlst.size()];
		formView.setData(tiposlst.toArray(tipos));
	}

	private void onDisplayReport(ActionEvent r) {
		List<PersonaDTO> target = ControllersFactory.getFactory().getPersonaController().readAll();
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setData(target);
		reporte.open();
	}
}