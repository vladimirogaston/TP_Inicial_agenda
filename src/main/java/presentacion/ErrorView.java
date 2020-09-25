package presentacion;

import java.util.List;

import javax.swing.JOptionPane;

public class ErrorView {
	
	public void showMessages(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void showMessages(List<String> messages) {
		StringBuilder builder = new StringBuilder();
		for(String message : messages) builder.append(message + "\n");
		showMessages(builder.toString());
	}
}
