package presentacion.views;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import dto.PersonaDTO;
import presentacion.views.swing.WorkbenchView;

public class WorkbenchDriverAdaptor {
	
	private WorkbenchView view = WorkbenchView.getInstance();
				
	public PersonaDTO getData() {
		int row = view.getTablaPersonas().getSelectedRow();
		PersonaDTO dto = new PersonaDTO.Builder(getValueAt(row, 0), getValueAt(row, 1))
				.email(getValueAt(row, 2))
				.tipoContacto(getValueAt(row, 4))
				.calle(getValueAt(row, 5))
				.altura(getValueAt(row, 6))
				.piso(getValueAt(row, 7))
				.dpto(getValueAt(row, 8))
				.localidad(getValueAt(row, 9))
				.provincia(getValueAt(row, 10))
				.pais(getValueAt(row, 11))
				.equipoFutbol(getValueAt(row, 12))
				.codigoPostal(getValueAt(row, 13))
				.build();
		
		try {
			dto.setIdPersona(Integer.parseInt(getValueAt(row, 14)));
		}catch(NumberFormatException e) {
			dto.setIdPersona(null);
		}
		if(!getValueAt(row, 3).isEmpty())
			try {
				dto.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(getValueAt(row, 3)));
			} catch (ParseException e) {
				dto.setFechaNacimiento(null);
			}
		return dto;
	}

	String getValueAt(int row, int column) {
		Object obj = view.getModelPersonas().getValueAt(row, column);
		if(obj != null) return view.getModelPersonas().getValueAt(row, column).toString();
		else return "";
	}
	
	public void setData(PersonaDTO [] personas) {
		for(PersonaDTO p : personas) {
			if(p != null) {
				Object[] fila = { p.getNombre(), p.getTelefono(), p.getEmail(), p.getFechaNacimiento(), p.getTipoContacto(),
						p.getCalle(), p.getAltura(), p.getPiso(), p.getDpto(), p.getLocalidad(), p.getProvincia(), p.getPais(),
						p.getEquipoFutbol(), p.getCodigoPostal(), p.getIdPersona() };
				view.getModelPersonas().addRow(fila);	
			}
		}
	}
	
	public void clearData() {
		view.getModelPersonas().setRowCount(0);
		view.getModelPersonas().setColumnCount(0);
		view.getModelPersonas().setColumnIdentifiers(view.getNombreColumnas());
	}
	
	public void open() {
		view.open();
	}
	
	public void setActionSave(ActionListener listener) {
		view.getBtnAgregar().addActionListener(listener);
	}

	public void setActionUpdate(ActionListener listener) {
		view.getBtnEditar().addActionListener(listener);
	}

	public void setActionDelete(ActionListener listener) {
		view.getBtnBorrar().addActionListener(listener);
	}

	public void setActionReport(ActionListener listener) {
		view.getBtnReporte().addActionListener(listener);
	}
}
