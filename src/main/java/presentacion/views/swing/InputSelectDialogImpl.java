package presentacion.views.swing;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputSelectDialogImpl {

	private JPanel contentPane;
	private JLabel textLabel;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private String title;
	
	public InputSelectDialogImpl() {
		contentPane = new JPanel(new FlowLayout());
		textLabel = new JLabel("Nombre");
		textField = new JTextField(15);
		textField.setText("");
		comboBox = new JComboBox<>();
		contentPane.add(textLabel);
		contentPane.add(textField);
		contentPane.add(comboBox);
		title = "";
	}
	
	public InputSelectDialogImpl title(String title) {
		assert title != null;
		assert title.trim().isEmpty();
		this.title = title;
		return this;
	}
	
	public InputSelectDialogImpl setProvincias(String [] str) {
		assert str != null;
		for(String aux : str) comboBox.addItem(aux);
		return this;
	}
	
	public InputSelectDialogImpl setText(String text) {
		assert text != null;
		textField.setText(text);
		return this;
	}
	
	public String [] displayForm() {
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
	
	private String [] getData() {
		Object provincia = comboBox.getSelectedItem();
		String prov = provincia == null ? null : provincia.toString();
		String nombre = textField.getText();
		if(nombre.trim().isEmpty()) {
			return null;
		}
		return new String [] {nombre, prov};
	}

	public InputSelectDialogImpl setNombreProvincia(String str) {
		assert str != null;
		comboBox.setSelectedItem(str);
		return this;
	}
}