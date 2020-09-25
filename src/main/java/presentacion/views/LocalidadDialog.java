package presentacion.views;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.LocalidadDTO;

public class LocalidadDialog extends JDialog {

	private static final long serialVersionUID = 2331647166985810760L;
	private JPanel contentPane;
	private JLabel textLabel;
	private JTextField textField;
	private JComboBox<String> comboBox;
	
	public LocalidadDialog() {
		contentPane = new JPanel(new FlowLayout());
		textLabel = new JLabel("Nombre");
		textField = new JTextField("");
		comboBox = new JComboBox<>();
		contentPane.add(textLabel);
		contentPane.add(textField);
		contentPane.add(comboBox);
	}
	
	public LocalidadDialog setProvincias(String [] nombresProvincias) {
		assert nombresProvincias != null;
		for(String nombre : nombresProvincias) comboBox.addItem(nombre);
		return this;
	}
	
	public LocalidadDialog setText(String text) {
		assert text != null;
		textField.setText(text);
		return this;
	}
	
	public LocalidadDTO displayForm() {
		int result = JOptionPane.showConfirmDialog(null, contentPane, "Localidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (result) {
		    case JOptionPane.OK_OPTION:
		        break;
		}
		return getData();
	}
	
	private LocalidadDTO getData() {
		Object provincia = comboBox.getSelectedItem();
		String prov = provincia == null ? null : provincia.toString();
		String nombre = textField.getText();
		if(nombre.strip().isEmpty()) return null;
		return new LocalidadDTO(null, nombre.toString(), prov);
	}
}
