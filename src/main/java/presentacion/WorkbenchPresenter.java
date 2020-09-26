package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactory;
import business_logic.PersonaController;
import presentacion.views.PersonaDriverAdaptor;
import presentacion.views.ReporteView;
import presentacion.views.WorkbenchDriverAdaptor;
import presentacion.views.swing.ErrorView;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class WorkbenchPresenter {

	private WorkbenchDriverAdaptor workbenchView;
	private PersonaDriverAdaptor formView;

	public WorkbenchPresenter(WorkbenchDriverAdaptor view, PersonaDriverAdaptor adaptor) {
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
		PersonaDTO dto = workbenchView.getData();
		if(dto != null) {
			PersonaController controller = ControllersFactory.getFactory().getPersonaController();
			controller.delete(dto);
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
			new ErrorView().showMessages(errors);
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
		formView.clearData();
		fillOptionsList();
		PersonaDTO target = workbenchView.getData();
		formView.setData(target);
		formView.open();
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
		ReporteView reporte = new ReporteView(target);
		reporte.mostrar();
	}
	
	public void onInit() {
		PersonaDTO [] personas = getData();
		if(personas != null) workbenchView.setData(personas);
		workbenchView.open();
	}
}
