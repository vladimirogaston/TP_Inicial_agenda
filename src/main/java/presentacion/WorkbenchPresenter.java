package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.PersonaController;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import presentacion.views.WorkbenchView;
import presentacion.views.swing.ErrorDialogImpl;
import presentacion.views.swing.PersonaViewImpl;
import presentacion.views.swing.ReporteViewImpl;

public class WorkbenchPresenter {

	private WorkbenchView workbenchView;
	private PersonaViewImpl formView;
	
	public WorkbenchPresenter(WorkbenchView view, PersonaViewImpl adaptor) {
		workbenchView = view;
		formView = adaptor;
		onInjectWorkbenchActions();
		onInjectFormActions();
	}
	
	private void onInjectWorkbenchActions() {
		workbenchView.setActionSave(a -> onDisplayFormForSave(a));
		workbenchView.setActionUpdate(a -> onDisplayFormForUpdate(a));
		workbenchView.setActionDelete(s -> onDelete(s));
		workbenchView.setActionReport(r -> onDisplayReport(r));
	}
	
	private void onInjectFormActions() {
		formView.setActionSave(p -> onSaveUpdate(p));
		formView.setActionPaisSelect(a->onUpdateProvinciasList(a));
		formView.setActionProvinciaSelect(a->onUpdateLocalidadesList(a));
	}
		
	private void onDelete(ActionEvent s) {
		PersonaDTO target = workbenchView.getData();
		if(target != null) {
			PersonaController controller = ControllersFactory.getFactory().getPersonaController();
			controller.delete(target.getId());
			workbenchView.clearData();
			PersonaDTO [] personas = getData();
	 		workbenchView.setData(personas);	
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
			workbenchView.clearData();
			workbenchView.setData(getData());
			formView.close();
		} else {
			new ErrorDialogImpl().showMessages(errors);
		}
	}	
	
	private PersonaDTO [] getData() {
		List<PersonaDTO> personas = ControllersFactory.getFactory().getPersonaController().readAll();
		return personas.toArray(new PersonaDTO [personas.size()]);
	}
	
	private void onUpdateProvinciasList(ActionEvent a) {
		String pais = formView.getNombrePaisSeleccionado();
		if(!pais.isEmpty()) {
			List<ProvinciaDTO> provinciaslst = ControllersFactory.getFactory().getProvinciaController().readByPais(pais);
			ProvinciaDTO [] target = new ProvinciaDTO [provinciaslst.size()];
			formView.setData(provinciaslst.toArray(target));
		}
	}

	private void onUpdateLocalidadesList(ActionEvent a) {
		String provincia = formView.getNombreProvinciaSeleccionada();
		if(!provincia.isEmpty()) {
			List<LocalidadDTO> localidades = ControllersFactory.getFactory().getLocalidadController().readByProvincia(provincia);
			LocalidadDTO [] target = new LocalidadDTO [localidades.size()];
			formView.setData(localidades.toArray(target));
		}
	}
	
	private void onDisplayFormForSave(ActionEvent a) {
		formView.clearData();
		fillOptionsList();
		formView.open();
	}

	private void onDisplayFormForUpdate(ActionEvent a) {
		PersonaDTO target = workbenchView.getData();
		if(target != null) {
			formView.clearData();
			
			List<TipoContactoDTO> tiposlst = ControllersFactory.getFactory().getTipoController().readAll();
			TipoContactoDTO [] tipos = new TipoContactoDTO[tiposlst.size()];
			formView.setData(tiposlst.toArray(tipos));
			
			List<PaisDTO> paiseslst = ControllersFactory.getFactory().getPaisController().readAll();
			PaisDTO [] paises = new PaisDTO [paiseslst.size()];
			formView.setData(paiseslst.toArray(paises));
						
			if(!target.getProvincia().isEmpty()) {
				List<ProvinciaDTO> provincialst = ControllersFactory.getFactory().getProvinciaController().readAll();
				ProvinciaDTO [] provincias = new ProvinciaDTO [provincialst.size()];
				formView.setData(provincialst.toArray(provincias));
			}
			
			if(!target.getLocalidad().isEmpty()) {
				List<LocalidadDTO> locslst = ControllersFactory.getFactory().getLocalidadController().readByProvincia(target.getProvincia());
				LocalidadDTO [] localidades = new LocalidadDTO [locslst.size()];
				formView.setData(locslst.toArray(localidades));
			}
			
			formView.setData(target);
			formView.open();	
		}
	}
	
	private void fillOptionsList() {
		List<TipoContactoDTO> tiposlst = ControllersFactory.getFactory().getTipoController().readAll();
		TipoContactoDTO [] tipos = new TipoContactoDTO[tiposlst.size()];
		formView.setData(tiposlst.toArray(tipos));
		List<PaisDTO> paiseslst = ControllersFactory.getFactory().getPaisController().readAll();
		PaisDTO [] paises = new PaisDTO [paiseslst.size()];
		formView.setData(paiseslst.toArray(paises));
	}
	
	private void onDisplayReport(ActionEvent r) {
		List<PersonaDTO> target = ControllersFactory.getFactory().getPersonaController().readAll();
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setData(target);
		reporte.open();
	}
	
	public void onInit() {
		PersonaDTO [] personas = getData();
		if(personas != null) workbenchView.setData(personas);
		workbenchView.open();
	}
}
