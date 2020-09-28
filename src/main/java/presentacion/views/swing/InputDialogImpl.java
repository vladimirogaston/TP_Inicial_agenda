package presentacion.views.swing;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputDialogImpl {
	
	private JPanel contentPane;
	private JLabel textLabel;
	private JTextField textField;
	private String title;
	
	public InputDialogImpl() {
		contentPane = new JPanel(new FlowLayout());
		textLabel = new JLabel("Nombre");
		textField = new JTextField(10);
		textField.setText("");
		contentPane.add(textLabel);
		contentPane.add(textField);
		title = "";
	}
	
	public InputDialogImpl title(String title) {
		assert title != null;
		assert title.trim().isEmpty();
		this.title = title;
		return this;
	}
	
	public InputDialogImpl setText(String text) {
		assert text != null;
		textField.setText(text);
		return this;
	}
	
	public String open() {
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
	
	private String getData() {
		String nombre = textField.getText();
		if(nombre.strip().isEmpty()) {
			return null;
		}
		return nombre.toString();
	}
}
