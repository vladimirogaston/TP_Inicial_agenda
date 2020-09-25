package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ControllersFactoryImpl;
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
		formView.setActionProvinciaSelect(a->onUpdateLocalidadesList(a));
	}
		
	private void onDelete(ActionEvent s) {
		PersonaDTO dto = workbenchView.getData();
		ControllersFactoryImpl.getInstance().getPersonaController().borrarPersona(dto);
		workbenchView.clearData();
		PersonaDTO [] personas = getData();
 		workbenchView.setData(personas);
	}
	
	private void onSaveUpdate(ActionEvent p) {
		PersonaDTO target = formView.getData();
		List<String> errors = target.validate();
		if (errors.isEmpty()) {
			if (target.getIdPersona() == null) {
				ControllersFactoryImpl.getInstance().getPersonaController().agregarPersona(target);
			} else {
				ControllersFactoryImpl.getInstance().getPersonaController().editar(target);
			}
			workbenchView.clearData();
			workbenchView.setData(getData());
			formView.close();
		} else {
			new ErrorView().showMessages(errors);
		}
	}	
	
	private PersonaDTO [] getData() {
		List<PersonaDTO> personas = ControllersFactoryImpl.getInstance().getPersonaController().obtenerPersonas();
		return personas.toArray(new PersonaDTO [personas.size()]);
	}

	private void onUpdateLocalidadesList(ActionEvent a) {
		String provincia = formView.getNombreProvinciaSeleccionada();
		if(!provincia.isEmpty()) {
			List<LocalidadDTO> localidades = ControllersFactoryImpl.getInstance().getLocalidadController().localidadesDisponibles();
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
		List<TipoContactoDTO> tiposlst = ControllersFactoryImpl.getInstance().getTipoController().tiposDisponibles();
		TipoContactoDTO [] tipos = new TipoContactoDTO[tiposlst.size()];
		formView.setData(tiposlst.toArray(tipos));
		List<PaisDTO> paiseslst = ControllersFactoryImpl.getInstance().getPaisController().paisesDisponibles();
		PaisDTO [] paises = new PaisDTO [paiseslst.size()];
		formView.setData(paiseslst.toArray(paises));
		List<ProvinciaDTO> provinciaslst = ControllersFactoryImpl.getInstance().getProvinciaController().provinciasDisponibles();
		formView.setData(provinciaslst.toArray(new ProvinciaDTO [provinciaslst.size()]));
	}
	
	private void onDisplayReport(ActionEvent r) {
		List<PersonaDTO> target = ControllersFactoryImpl.getInstance().getPersonaController().obtenerPersonas();
		ReporteView reporte = new ReporteView(target);
		reporte.mostrar();
	}
	
	public void onInit() {
		PersonaDTO [] personas = getData();
		if(personas != null) workbenchView.setData(personas);
		workbenchView.open();
	}
}
