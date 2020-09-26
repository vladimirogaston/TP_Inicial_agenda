package presentacion.views.swing;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;

public class LocalidadDialog extends JDialog {

	private static final long serialVersionUID = 2331647166985810760L;
	private JPanel contentPane;
	private JLabel textLabel;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private String title;
	
	public LocalidadDialog() {
		contentPane = new JPanel(new FlowLayout());
		textLabel = new JLabel("Nombre");
		textField = new JTextField(10);
		textField.setText("");
		comboBox = new JComboBox<>();
		contentPane.add(textLabel);
		contentPane.add(textField);
		contentPane.add(comboBox);
		title = "";
	}
	
	public LocalidadDialog title(String title) {
		assert title != null;
		assert title.trim().isEmpty();
		this.title = title;
		return this;
	}
	
	public LocalidadDialog setProvincias(ProvinciaDTO [] nombresProvincias) {
		assert nombresProvincias != null;
		for(ProvinciaDTO dto : nombresProvincias) comboBox.addItem(dto.getNombre());
		return this;
	}
	
	public LocalidadDialog setText(String text) {
		assert text != null;
		textField.setText(text);
		return this;
	}
	
	public LocalidadDTO displayForm() {
		int result = JOptionPane.showConfirmDialog(null, contentPane, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (result) {
		    case JOptionPane.OK_OPTION:
		        break;
		    case JOptionPane.OK_CANCEL_OPTION:
		    	textField.setText("");
		    	break;
		}
		return getData();
	}
	
	private LocalidadDTO getData() {
		Object provincia = comboBox.getSelectedItem();
		String prov = provincia == null ? null : provincia.toString();
		String nombre = textField.getText();
		if(nombre.trim().isEmpty()) {
			return null;
		}
		return new LocalidadDTO(null, nombre.toString(), prov);
	}

	public LocalidadDialog setNombreProvincia(String provincia) {
		assert provincia != null;
		comboBox.setSelectedItem(provincia);
		return this;
	}
}
